package com.example.springboottutorial.domain.user

import com.example.springboottutorial.domain.user.dto.response.MeResponse
import com.example.springboottutorial.global.dto.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "user")
@RestController
@RequestMapping("/v1/user")
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "내 정보 조회")
    @GetMapping("/me")
    fun getMe(@AuthenticationPrincipal email: String): ApiResponse<MeResponse> {
        val user = userService.getMe(email)
        return ApiResponse.success(data = user)
    }
}