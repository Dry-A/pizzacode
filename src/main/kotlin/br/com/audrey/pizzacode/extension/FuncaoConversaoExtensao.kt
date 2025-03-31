package br.com.audrey.pizzacode.extension

import br.com.audrey.pizzacode.model.Cliente

import br.com.audrey.pizzacode.controller.request.PostClienteRequest
import br.com.audrey.pizzacode.controller.request.PutClienteRequest


fun PostClienteRequest.toClienteModel(): Cliente {
    return Cliente(
        nome = this.nome,
        telefone = this.telefone,
        endereco = this.endereco
    )
}
fun PutClienteRequest.toClienteModel(id: Long): Cliente {
    return Cliente(
        id = id,
        nome = this.nome,
        telefone = this.telefone,
        endereco = this.endereco
    )
}
