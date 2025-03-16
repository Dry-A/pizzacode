package br.com.audrey.pizzacode.model

data class Cliente(
    val id: Long,
    val nome: String,
    val telefone: String,
    val endereco: String
)