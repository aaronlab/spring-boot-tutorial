package com.example.springboottutorial.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class GlobalConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        // LocalDataTime 포맷 설정
        val javaTimeModule = JavaTimeModule()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        javaTimeModule.addSerializer(
            LocalDateTime::class.java,
            LocalDateTimeSerializer(formatter)
        )

        // ObjectMapper 설정
        val objectMapper = ObjectMapper()

        // JavaTimeModule 설정
        objectMapper.registerModule(javaTimeModule)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        // KotlinModule 설정
        objectMapper.registerModules(KotlinModule.Builder().build())

        return objectMapper
    }
}