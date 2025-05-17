package com.example.springboottutorial.domain.auth.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class SignupRequest(
    @field:Schema(
        example = "aaron@aaron.aaron"
    )
    @field:Email
    val email: String,

    @field:Schema(
        example = "Aaron"
    )
    @field:Size(min = 2, max = 20)
    val name: String,

    @field:Schema(
        example = "password"
    )
    @field:Size(min = 8, max = 20)
    val password: String
)
