package com.example.springboottutorial.global.config

import com.example.springboottutorial.domain.auth.filter.JwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter
) {
    /**
     * Spring Security 필터 체인
     */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/webjars/**"
                    ).permitAll()
                    .anyRequest().permitAll()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    /**
     * 비밀번호 암호화에 사용할 PasswordEncoder
     */
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    /**
     * Spring Security 에서 사용할 UserDetailsService
     */
    @Bean
    fun userDetailService(): UserDetailsService {
        return UserDetailsService { _ ->
            throw UsernameNotFoundException("Forbidden")
        }
    }
}