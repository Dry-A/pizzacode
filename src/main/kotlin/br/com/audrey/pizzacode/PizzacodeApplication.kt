package br.com.audrey.pizzacode

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PizzacodeApplication

fun main(args: Array<String>) {
    runApplication<PizzacodeApplication>(*args)
}
