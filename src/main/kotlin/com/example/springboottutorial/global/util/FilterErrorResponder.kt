package com.example.springboottutorial.global.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.example.springboottutorial.global.dto.response.ApiResponse
import com.example.springboottutorial.global.exception.ErrorCode
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType

object FilterErrorResponder {
    private val objectMapper = ObjectMapper()

    fun writeErrorResponse(
        response: HttpServletResponse,
        error: ErrorCode
    ) {
        response.status = error.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val body = ApiResponse.fail<Unit>(error.message, error.code)
        response.writer.write(objectMapper.writeValueAsString(body))
        response.writer.flush()
    }
}
