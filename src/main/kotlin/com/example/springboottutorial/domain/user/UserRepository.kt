package com.example.springboottutorial.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, String> {
    /**
     * 이메일로 사용자 조회
     */
    fun findByEmail(email: String): UserEntity?
}