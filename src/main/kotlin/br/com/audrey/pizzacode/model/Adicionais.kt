package br.com.audrey.pizzacode.model

enum class Adicionais(val descricao: String, val preco: Double) {
    EXTRA_QUEIJO("Extra Queijo", 5.0),
    BACON("Bacon", 6.0),
    ALCAPARRA("Alcaparra", 4.0),
    OLIVA("Oliva", 3.0),
    CATUPIRY("Catupiry", 4.0),
}
