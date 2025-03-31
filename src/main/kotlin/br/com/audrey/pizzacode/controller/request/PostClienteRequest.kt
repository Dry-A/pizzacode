package br.com.audrey.pizzacode.controller.request

import br.com.audrey.pizzacode.model.Cliente

data class PostClienteRequest(
    var nome: String,
    var telefone: String,
    var endereco: String
) {
    fun toClienteModel(): Cliente {
        return Cliente(
            nome = this.nome,
            telefone = this.telefone,
            endereco = this.endereco
        )
    }

}