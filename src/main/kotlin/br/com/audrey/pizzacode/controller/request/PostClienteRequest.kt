package br.com.audrey.pizzacode.controller.request

data class PostClienteRequest(
    var nome: String,
    var telefone: String,
    var endereco: String
)
