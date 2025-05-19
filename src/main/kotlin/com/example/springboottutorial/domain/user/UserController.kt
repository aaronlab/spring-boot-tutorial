package com.example.springboottutorial.domain.user

import com.example.springboottutorial.domain.user.dto.request.UpdateUserRequest
import com.example.springboottutorial.domain.user.dto.response.CheckEmailResponse
import com.example.springboottutorial.domain.user.dto.response.MeResponse
import com.example.springboottutorial.global.annotation.Public
import com.example.springboottutorial.global.dto.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "user")
@RestController
@RequestMapping("/v1/user")
class UserController(
    private val userService: UserService
) {
    @Public
    @Operation(summary = "이메일 중복 체크")
    @GetMapping("/check-email")
    fun checkEmail(
        @Schema(example = "aaron@aaron.aaron")
        @Email
        @NotNull
        @RequestParam email: String
    ): ApiResponse<CheckEmailResponse> {
        val data = userService.checkEmail(email)
        return ApiResponse.success(data = data)
    }

    @Operation(summary = "내 정보 조회")
    @GetMapping("/me")
    fun getMe(@AuthenticationPrincipal id: String): ApiResponse<MeResponse> {
        val user = userService.getMe(id)
        return ApiResponse.success(data = user)
    }

    @Operation(summary = "내 정보 수정")
    @PatchMapping("/me")
    fun updateMe(
        @AuthenticationPrincipal id: String,
        @Valid @RequestBody request: UpdateUserRequest
    ): ApiResponse<MeResponse> {
        val updatedUser = userService.updateMe(id, request)
        return ApiResponse.success(data = updatedUser)
    }
}
