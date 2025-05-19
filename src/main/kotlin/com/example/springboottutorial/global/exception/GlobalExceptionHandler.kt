package com.example.springboottutorial.global.exception

import com.example.springboottutorial.global.dto.response.ApiResponse
import com.example.springboottutorial.global.extension.appError
import com.example.springboottutorial.global.util.logger
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = logger()

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ApiResponse<String>> {
        logger.appError("Exception occurred", e)

        return ResponseEntity
            .status(e.errorCode.status)
            .body(ApiResponse.fail(errorCode = e.errorCode))
    }

    private fun handleInvalidRequest(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        logger.appError("Exception occurred", e)

        val errorCode = ErrorCode.INVALID_REQUEST

        return ResponseEntity
            .status(errorCode.status)
            .body(ApiResponse.fail(errorCode = errorCode))
    }

    private fun handleInternalServerError(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        logger.appError("Exception occurred", e)

        val errorCode = ErrorCode.INTERNAL_SERVER_ERROR

        return ResponseEntity
            .status(errorCode.status)
            .body(ApiResponse.fail(errorCode = errorCode))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        logger.appError("Exception occurred", e)

        val errorCode = ErrorCode.INTERNAL_SERVER_ERROR

        return ResponseEntity
            .status(errorCode.status)
            .body(ApiResponse.fail(errorCode = errorCode))
    }

    @ExceptionHandler(ErrorResponseException::class)
    fun handleErrorResponseException(e: ErrorResponseException): ResponseEntity<ApiResponse<Unit>> {
        logger.appError("Exception occurred with Status: ${e.statusCode}")
        return if (e.statusCode.value() in 400..499) {
            handleInvalidRequest(e)
        } else {
            handleInternalServerError(e)
        }
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleHttpMessageConversionException(e: HttpMessageConversionException) = handleInvalidRequest(e)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException) = handleInvalidRequest(e)

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException) = handleInvalidRequest(e)

    @ExceptionHandler(MethodArgumentConversionNotSupportedException::class)
    fun handleMethodArgumentConversionNotSupportedException(e: MethodArgumentConversionNotSupportedException) =
        handleInvalidRequest(e)
}