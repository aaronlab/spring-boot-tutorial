package com.example.springboottutorial.domain.auth

import com.example.springboottutorial.global.annotation.Public
import com.example.springboottutorial.domain.auth.dto.request.SigninRequest
import com.example.springboottutorial.domain.auth.dto.request.SignupRequest
import com.example.springboottutorial.domain.auth.dto.response.SigninResponse
import com.example.springboottutorial.global.dto.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Public
@Tag(name = "auth")
@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService
) {
    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody request: SignupRequest): ApiResponse<SigninResponse> {
        val data = authService.signup(request)

        return ApiResponse.success(data = data)
    }

    @Operation(summary = "로그인")
    @PostMapping("/signin")
    fun signin(@Valid @RequestBody request: SigninRequest): ApiResponse<SigninResponse> {
        val data = authService.signin(request)

        return ApiResponse.success(data = data)
    }
}
