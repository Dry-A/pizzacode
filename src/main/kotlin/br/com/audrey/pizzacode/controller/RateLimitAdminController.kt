package br.com.audrey.pizzacode.controller

import br.com.audrey.pizzacode.interceptor.RateLimitInterceptor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/rate-limit")
class RateLimitAdminController(private val rateLimitInterceptor: RateLimitInterceptor) {

    @GetMapping("/stats")
    fun getRateLimitStats(): Map<String, Any> {
        return rateLimitInterceptor.getStats()
    }
}
