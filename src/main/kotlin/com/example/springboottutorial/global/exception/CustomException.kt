package com.example.springboottutorial.global.exception

class CustomException(
    val errorCode: ErrorCode
): RuntimeException(errorCode.message) {
    companion object {
        fun of(code: ErrorCode): CustomException = CustomException(code)
    }
}