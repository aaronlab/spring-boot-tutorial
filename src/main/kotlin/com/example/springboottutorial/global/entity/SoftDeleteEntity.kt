package com.example.springboottutorial.global.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class SoftDeleteEntity : BaseTimeEntity() {
    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null

    fun isDeleted(): Boolean = deletedAt != null

    fun delete() {
        deletedAt = LocalDateTime.now()
    }
}