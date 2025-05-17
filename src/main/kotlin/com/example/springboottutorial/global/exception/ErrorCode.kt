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

    // USER
    USER_NOT_FOUND("USER-001", "사용자를 찾을 수 없습니다.", 404),

    // COMMON
    INVALID_REQUEST("COMMON-001", "잘못된 요청입니다.", 400),
    INTERNAL_SERVER_ERROR("COMMON-002", "서버 오류가 발생했습니다.", 500),
}