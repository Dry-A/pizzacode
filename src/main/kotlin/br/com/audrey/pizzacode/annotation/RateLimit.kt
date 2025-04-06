package br.com.audrey.pizzacode.annotation

import java.lang.annotation.ElementType
import kotlin.annotation.Retention
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Target
import kotlin.annotation.AnnotationTarget

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RateLimit(
    val limit: Int = 3,
    val period: Int = 30, //em segundos
    val userBased: Boolean = false  // Se true, usa ID do usuário em vez de IP

)
