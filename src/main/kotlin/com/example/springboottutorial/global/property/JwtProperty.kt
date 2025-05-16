package com.example.springboottutorial.global.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperty {
    /**
     * JWT 비밀키
     */
    lateinit var secret: String

    /**
     * JWT 만료 시간 (초 단위)
     */
    var expiration: Long = 0
}