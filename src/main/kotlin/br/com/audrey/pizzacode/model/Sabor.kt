package br.com.audrey.pizzacode.model

enum class Sabor (val descricao: String, val preco: Double) {
    MUSSARELA("molho, mussarela e orégano", 29.90),
    CALABRESA("Calabresa com cebola", 27.90),
    MARGUERITA("Marguerita", 25.90),
    FRANGO("Frango com catupiry", 28.90),
    MARGUERITA_COM_FRANGO("Marguerita com Frango", 30.90),
    ATUM("Atum com cebola", 31.90),
    CALABRESA_COM_MUSSARELA("Calabresa com Mussarela", 32.90),
    PEPPERONI("Pepperoni", 33.90),
    VEGANA("Vegana", 35.90),
    PORTUGUESA("Portuguesa", 34.90)
}