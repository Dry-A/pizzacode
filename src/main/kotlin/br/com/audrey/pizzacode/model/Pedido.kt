package br.com.audrey.pizzacode.model

data class Pedido(
    val id: Long,
    val cliente: Cliente,
    val pizzas: List<Pizza>,
    val observacao: String = "",  // Observação geral do pedido
    val status: StatusPedido = StatusPedido.RECEBIDO
) {
    // Função para calcular o preço total do pedido
    fun precoTotal(): Double {
        var precoTotal = 0.0
        pizzas.forEach { pizza ->
            precoTotal += pizza.precoTotal() // Soma o preço de cada pizza
        }
        return precoTotal
    }

    // Função para imprimir a ordem para o pizzaiolo
    fun imprimirParaPizzaiolo(): String {
        val sb = StringBuilder()
        sb.append("Pedido nº $id\n")
        pizzas.forEachIndexed { index, pizza ->
            sb.append("\nPizza ${index + 1}: ${pizza.sabor1.descricao}")
            pizza.sabor2?.let {
                sb.append(" + ${it.descricao}")
            }
            sb.append("\nAdicionais: ${pizza.adicionais.joinToString { it.descricao }}")
            if (pizza.observacao.isNotEmpty()) {
                sb.append("\nObservações: ${pizza.observacao}")
            }
            sb.append("\n")
        }
        sb.append("\nObservação geral do pedido: $observacao\n")
        return sb.toString()
    }

    // Função para imprimir a ordem para o motoboy
    fun imprimirParaMotoboy(): String {
        val sb = StringBuilder()
        sb.append("Pedido nº $id\n")
        sb.append("Cliente: ${cliente.nome}\n")
        sb.append("Endereço: ${cliente.endereco}\n")
        sb.append("Telefone: ${cliente.telefone}\n")
        sb.append("\nPizzas: \n")
        pizzas.forEachIndexed { index, pizza ->
            sb.append("Pizza ${index + 1}: ${pizza.sabor1.descricao}")
            pizza.sabor2?.let {
                sb.append(" + ${it.descricao}")
            }
            sb.append("\nAdicionais: ${pizza.adicionais.joinToString { it.descricao }}\n")
        }
        sb.append("\nObservação geral do pedido: $observacao\n")
        return sb.toString()
    }
}
