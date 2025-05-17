package com.example.springboottutorial.domain.auth

import com.example.springboottutorial.domain.auth.dto.request.SigninRequest
import com.example.springboottutorial.domain.auth.dto.request.SignupRequest
import com.example.springboottutorial.domain.auth.dto.response.SigninResponse
import com.example.springboottutorial.domain.auth.util.JwtUtil
import com.example.springboottutorial.domain.user.UserEntity
import com.example.springboottutorial.domain.user.UserRepository
import com.example.springboottutorial.global.exception.CustomException
import com.example.springboottutorial.global.exception.ErrorCode
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtUtil: JwtUtil
) {
    /**
     * 회원가입
     */
    fun signup(request: SignupRequest): SigninResponse {
        if (userRepository.findByEmailAndDeletedAtIsNull(request.email) !== null) {
            throw CustomException.of(ErrorCode.EMAIL_ALREADY_EXISTS)
        }

        val hashedPassword = passwordEncoder.encode(request.password)
        val user = UserEntity(
            email = request.email,
            name = request.name,
            password = hashedPassword
        )

        userRepository.save(user)

        return signin(SigninRequest(email = request.email, password = request.password))
    }

    /**
     * 로그인
     */
    fun signin(request: SigninRequest): SigninResponse {
        val user = userRepository.findByEmailAndDeletedAtIsNull(request.email)
            ?: throw CustomException.of(ErrorCode.INVALID_CREDENTIALS)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }

        val token = jwtUtil.generateToken(user.email)
        return SigninResponse(token = token)
    }
}