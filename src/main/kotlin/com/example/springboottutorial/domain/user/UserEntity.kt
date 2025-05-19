package com.example.springboottutorial.domain.user

import com.example.springboottutorial.global.entity.SoftDeleteEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @Column(length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var password: String,

    ) : SoftDeleteEntity()