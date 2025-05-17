package com.example.springboottutorial.global.exception

import com.example.springboottutorial.global.dto.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity
            .status(e.errorCode.status)
            .body(ApiResponse.fail(errorCode = e.errorCode))
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleHttpMessageConversionException(e: HttpMessageConversionException): ResponseEntity<ApiResponse<Unit>> {
        val errorCode = ErrorCode.INVALID_REQUEST

        println("!!!")
        println(e.message)
        e.printStackTrace()

        return ResponseEntity
            .status(errorCode.status)
            .body(ApiResponse.fail(errorCode = errorCode))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        val errorCode = ErrorCode.INTERNAL_SERVER_ERROR

        return ResponseEntity
            .status(errorCode.status)
            .body(ApiResponse.fail(errorCode = errorCode))
    }
}