package br.com.audrey.pizzacode.exception

import java.time.Instant

data class RateLimitExceededResponse (
    val timestamp: Instant = Instant.now(),
    val status: Int = 429,
    val error: String = "Muitas requisições! Tente novamente mais tarde.",
    val message: String,
    val resetAt: Instant
)