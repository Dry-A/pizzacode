package br.com.audrey.pizzacode.model

enum class StatusPedido {
    RECEBIDO,  // Pedido foi recebido
    PREPARANDO,  // Pedido está sendo feito
    PRONTO,  // Pedido está pronto para entrega
    ENTREGUE,  // Pedido foi entregue ao cliente
    CANCELADO  // Pedido foi cancelado
}