package com.example.springboottutorial.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, String> {
    /**
     * 이메일로 사용자 조회(Soft Delete 처리된 사용자 제외)
     */
    fun findByEmailAndDeletedAtIsNull(email: String): UserEntity?

    /**
     * 이메일로 사용자 조회(Soft Delete 처리된 사용자 포함)
     */
    fun findByEmail(email: String): UserEntity?

    /**
     * 아이디로 사용자 조회(Soft Delete 처리된 사용자 제외)
     */
    fun findByIdAndDeletedAtIsNull(id: String): UserEntity?
}