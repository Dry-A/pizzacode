package br.com.audrey.pizzacode.extension

import br.com.audrey.pizzacode.model.Cliente

fun Cliente.comEnderecoMascarado(): Cliente {
    val enderecoMascarado = this.endereco.split(" ").joinToString(" ") { palavra ->
        if (palavra.length <= 2) palavra
        else palavra.substring(0, 2) + "*".repeat(palavra.length - 2)
    }

    return this.copy(endereco = enderecoMascarado)
}