package com.example.springboottutorial.domain.auth.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class SigninRequest(
    @field:Schema(example = "aaron@aaron.aaron")
    @field:Email
    val email: String,

    @field:Schema(example = "password")
    @field:Size(min = 8, max = 20)
    val password: String
)
