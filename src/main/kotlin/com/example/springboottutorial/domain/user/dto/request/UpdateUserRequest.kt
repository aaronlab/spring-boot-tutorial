package com.example.springboottutorial.domain.user.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UpdateUserRequest(
    @field:Schema(example = "aaron.modified@aaron.aaron")
    @field:Email
    val email: String? = null,

    @field:Schema(example = "Aaron Modified")
    @field:Size(min = 2, max = 20)
    val name: String? = null,

    @field:Schema(example = "password")
    @field:Size(min = 8, max = 20)
    val password: String? = null
)
