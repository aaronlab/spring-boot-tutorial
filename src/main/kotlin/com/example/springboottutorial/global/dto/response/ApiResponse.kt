package com.example.springboottutorial.global.dto.response

import com.example.springboottutorial.global.exception.ErrorCode

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val errorCode: String? = null,
) {
    companion object {
        fun <T> success(
            message: String = "성공",
            data: T? = null
        ): ApiResponse<T> = ApiResponse(true, message, data, null)

        fun <T> fail(
            message: String = "실패",
            errorCode: String? = null,
        ): ApiResponse<T> = ApiResponse(false, message, null, errorCode)

        fun <T> fail(
            errorCode: ErrorCode
        ): ApiResponse<T> = ApiResponse(
            success = false,
            message = errorCode.message,
            data = null,
            errorCode = errorCode.code
        )
    }
}
