package br.com.audrey.pizzacode.aspect

import br.com.audrey.pizzacode.annotation.RateLimit
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.server.ResponseStatusException
import java.util.concurrent.TimeUnit

class RateLimitAspect(
    private val redisTemplate: RedisTemplate<String, String>,
) {

    @Around("@annotation(br.com.audrey.pizzacode.annotation.RateLimit)")
    fun rateLimit(joinPoint: ProceedingJoinPoint): Any {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val clientIp = request.remoteAddr

        val signature = joinPoint.signature as MethodSignature
        val method = signature.method
        val rateLimit = method.getAnnotation(RateLimit::class.java)

        val key = "ratelimit:$clientIp:${method.name}"
        val count = redisTemplate.opsForValue().increment(key, 1L) ?: 1L

        if (count == 1L) {
            redisTemplate.expire(key, rateLimit.period.toLong(), TimeUnit.SECONDS)
        }

        if (count > rateLimit.limit) {
            throw ResponseStatusException(
                HttpStatus.TOO_MANY_REQUESTS,
                "Limite de chamadas excedido. Tente novamente em ${rateLimit.period} segundos."
            )
        }

        return joinPoint.proceed()
    }
}