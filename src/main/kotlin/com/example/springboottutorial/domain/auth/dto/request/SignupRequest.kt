package com.example.springboottutorial.domain.auth.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class SignupRequest(
        @field:Schema(
        example = "aaron@aaron.aaron"
    )
    val email: String,

    @field:Schema(
        example = "password"
    )
    val password: String
)
