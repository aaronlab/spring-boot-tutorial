package com.example.springboottutorial.domain.user

import com.example.springboottutorial.domain.user.dto.response.MeResponse
import com.example.springboottutorial.global.exception.CustomException
import com.example.springboottutorial.global.exception.ErrorCode
import com.example.springboottutorial.global.extension.appDebug
import com.example.springboottutorial.global.util.logger
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository
) {
    private val logger = logger()

    fun getMe(email: String): MeResponse {
        logger.appDebug("UserService.getMe() called with email: $email")

        val user = userRepository.findByEmailAndDeletedAtIsNull(email)
            ?: throw CustomException.of(ErrorCode.USER_NOT_FOUND)

        return MeResponse(
            email = user.email,
            name = user.name,
            createdAt = user.createdAt
        )
    }
}