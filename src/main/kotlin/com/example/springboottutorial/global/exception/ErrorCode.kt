package com.example.springboottutorial.global.exception

enum class ErrorCode(
    val code: String,
    val message: String,
    val status: Int
) {
    // AUTH
    UNAUTHORIZED("AUTH-001", "인증되지 않은 사용자입니다.", 401),
    EMAIL_ALREADY_EXISTS("AUTH-002", "이미 존재하는 이메일입니다.", 400),
    INVALID_CREDENTIALS("AUTH-003", "잘못된 이메일 또는 비밀번호입니다.", 401),
}