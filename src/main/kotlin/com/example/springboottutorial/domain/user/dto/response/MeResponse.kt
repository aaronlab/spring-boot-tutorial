package com.example.springboottutorial.domain.user.dto.response

import java.time.LocalDateTime

data class MeResponse(
    val email: String,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
