package br.com.audrey.pizzacode.model

data class Cliente(
    var id: Long,
    var nome: String,
    var telefone: String,
    var endereco: String
)