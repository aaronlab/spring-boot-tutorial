package com.example.springboottutorial.global.entity

import jakarta.persistence.Column
import java.time.LocalDateTime

abstract class SoftDeleteEntity : BaseTimeEntity() {
    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null

    fun isDeleted(): Boolean = deletedAt != null

    fun delete() {
        deletedAt = LocalDateTime.now()
    }
}