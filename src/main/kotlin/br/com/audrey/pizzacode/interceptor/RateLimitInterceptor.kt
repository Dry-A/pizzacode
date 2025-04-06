package br.com.audrey.pizzacode.interceptor

import br.com.audrey.pizzacode.annotation.RateLimit
import br.com.audrey.pizzacode.exception.RateLimitExceededResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

@Component
class RateLimitInterceptor(private val objectMapper: ObjectMapper) : HandlerInterceptor {

    private val rateLimitStore = ConcurrentHashMap<String, RequestCount>()

    data class RequestCount(
        var count: Int = 1,
        val resetAt: Instant
    )

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) return true

        val rateLimit = handler.method.getAnnotation(RateLimit::class.java) ?: return true

        val key = buildKey(request, handler, rateLimit)

        val now = Instant.now()
        val requestCount = rateLimitStore.compute(key) { _, existing ->
            if (existing == null || existing.resetAt.isBefore(now)) {
                RequestCount(1, now.plusSeconds(rateLimit.period.toLong()))
            } else {
                existing.count++
                existing
            }
        }!!

        // Adicionar cabeçalhos de rate limit
        response.setHeader("X-RateLimit-Limit", rateLimit.limit.toString())
        response.setHeader("X-RateLimit-Remaining", (rateLimit.limit - requestCount.count).toString())
        response.setHeader("X-RateLimit-Reset", requestCount.resetAt.epochSecond.toString())

        if (requestCount.count > rateLimit.limit) {
            response.status = 429
            response.setHeader("Retry-After", (requestCount.resetAt.epochSecond - now.epochSecond).toString())
            response.contentType = "application/json"

            val responseBody = RateLimitExceededResponse(
                message = "Você excedeu o limite de ${rateLimit.limit} requisições em ${rateLimit.period} segundos. " +
                        "Por favor, tente novamente em ${requestCount.resetAt.epochSecond - now.epochSecond} segundos.",
                resetAt = requestCount.resetAt
            )

            objectMapper.writeValue(response.writer, responseBody)
            return false
        }

        return true
    }

    private fun buildKey(request: HttpServletRequest, handler: HandlerMethod, rateLimit: RateLimit): String {
        val methodName = "${handler.beanType.name}.${handler.method.name}"

        val identifier = if (rateLimit.userBased) {
            val auth = SecurityContextHolder.getContext().authentication
            auth?.name ?: request.remoteAddr
        } else {
            request.remoteAddr
        }

        return "$methodName:$identifier"
    }

    // Método para fornecer estatísticas de uso para o dashboard
    fun getStats(): Map<String, Any> {
        val now = Instant.now()
        val stats = mutableMapOf<String, Any>()

        // Estatísticas gerais
        stats["totalActiveKeys"] = rateLimitStore.size

        // Remover entradas expiradas antes de calcular estatísticas
        rateLimitStore.entries.removeIf { it.value.resetAt.isBefore(now) }

        // Estatísticas detalhadas por endpoint/usuário
        val detailedStats = rateLimitStore.entries.groupBy(
            { entry -> entry.key.substringBefore(":") }, // Agrupa por endpoint
            { entry ->
                mapOf(
                    "identifier" to entry.key.substringAfter(":"),
                    "count" to entry.value.count,
                    "resetAt" to entry.value.resetAt.toString()
                )
            }
        )

        stats["endpoints"] = detailedStats
        return stats
    }
}