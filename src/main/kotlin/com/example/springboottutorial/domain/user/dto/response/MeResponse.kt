package com.example.springboottutorial.domain.user.dto.response

import com.example.springboottutorial.domain.user.UserEntity
import java.time.LocalDateTime

data class MeResponse(
    val id: String,
    val email: String,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun fromEntity(userEntity: UserEntity) = MeResponse(
            id = userEntity.id,
            email = userEntity.email,
            name = userEntity.name,
            createdAt = userEntity.createdAt,
            updatedAt = userEntity.updatedAt
        )
    }
}
