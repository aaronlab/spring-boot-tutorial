package com.example.springboottutorial.domain.auth.filter

import com.example.springboottutorial.annotation.Public
import com.example.springboottutorial.domain.auth.util.JwtUtil
import com.example.springboottutorial.global.exception.CustomException
import com.example.springboottutorial.global.exception.ErrorCode
import com.example.springboottutorial.global.util.FilterErrorResponder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Component
class JwtAuthFilter(
    private val jwtUtil: JwtUtil,
    private val introspector: HandlerMappingIntrospector
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val handler = introspector.getMatchableHandlerMapping(request)?.getHandler(request)?.handler
        val handlerMethod = handler as? HandlerMethod

        val uri = request.requestURI
        val isSwagger = uri.startsWith("/v3/api-docs") ||
                uri.startsWith("/swagger-ui") ||
                uri.startsWith("/webjars")

        val isPublic = handlerMethod?.hasMethodAnnotation(Public::class.java) == true ||
                handlerMethod?.beanType?.isAnnotationPresent(Public::class.java) == true ||
                isSwagger

        if (!isPublic) {
            val authHeader = request.getHeader("Authorization")

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                val error = ErrorCode.UNAUTHORIZED

                FilterErrorResponder.writeErrorResponse(response, error)
                return
            }

            val token = authHeader.removePrefix("Bearer ").trim()
            if (!jwtUtil.validateToken(token)) {
                val error = ErrorCode.UNAUTHORIZED

                FilterErrorResponder.writeErrorResponse(response, error)
                return
            }

            val email = jwtUtil.getEmailFromToken(token)
            val authentication = UsernamePasswordAuthenticationToken(email, null, emptyList())
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}