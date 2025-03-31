package br.com.audrey.pizzacode.controller.request

data class PutClienteRequest(
    var nome: String,
    var telefone: String,
    var endereco: String
)
