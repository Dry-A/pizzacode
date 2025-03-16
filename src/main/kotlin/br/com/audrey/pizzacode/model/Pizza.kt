package br.com.audrey.pizzacode.model

data class Pizza(
    val id: Long,
    val sabor1: Sabor,
    val sabor2: Sabor? = null,
    val ingredientesRemovidos: List<String> = emptyList(),
    val adicionais: List<Adicionais> = emptyList(),  // Lista de adicionais
    val observacao: String = ""  // Observações do cliente (ex: "Pouco orégano")
) {

    fun precoTotal(): Double {
        var precoTotal = sabor1.preco // Acessa o preço do primeiro sabor
        sabor2?.let {
            precoTotal += it.preco // Se houver o segundo sabor, adiciona o preço dele
        }
        // Agora, adiciona os preços dos adicionais
        adicionais.forEach { precoTotal += it.preco }
        return precoTotal

    }
}
