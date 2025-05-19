package com.example.springboottutorial.domain.user

import com.example.springboottutorial.domain.user.dto.request.UpdateUserRequest
import com.example.springboottutorial.domain.user.dto.response.CheckEmailResponse
import com.example.springboottutorial.domain.user.dto.response.MeResponse
import com.example.springboottutorial.global.exception.CustomException
import com.example.springboottutorial.global.exception.ErrorCode
import com.example.springboottutorial.global.extension.appDebug
import com.example.springboottutorial.global.util.logger
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    private val logger = logger()

    /**
     * 이메일 중복 체크
     */
    fun checkEmail(email: String): CheckEmailResponse {
        logger.appDebug("UserService.checkEmail() called with email: $email")

        // 사용자 조회
        val user = userRepository.findByEmail(email)

        return CheckEmailResponse(isEmailExists = user != null)
    }

    /**
     * 내 정보 조회
     */
    fun getMe(id: String): MeResponse {
        logger.appDebug("UserService.getMe() called with id: $id")

        val user = userRepository.findByIdAndDeletedAtIsNull(id)
            ?: throw CustomException.of(ErrorCode.USER_NOT_FOUND)

        return MeResponse.fromEntity(user)
    }

    /**
     * 내 정보 수정
     */
    fun updateMe(id: String, request: UpdateUserRequest): MeResponse {
        logger.appDebug("UserService.getMe() called with id: $id, request: $request")

        // 사용자 조회
        val user = userRepository.findByIdAndDeletedAtIsNull(id)
            ?: throw CustomException.of(ErrorCode.USER_NOT_FOUND)

        // 이메일 중복 체크
        if (request.email != null) {
            val targetUser = userRepository.findByEmail(request.email)

            // 이메일이 중복된 경우
            if (targetUser != null) {
                throw CustomException.of(ErrorCode.EMAIL_ALREADY_EXISTS)
            }
        }

        // 업데이트
        request.email?.let { user.email = it }
        request.name?.let { user.name = it }
        request.password?.let { user.password = passwordEncoder.encode(it) }

        // 저장
        userRepository.save(user)

        return MeResponse.fromEntity(user)
    }
}
