package com.rr.rush.romans.service

import com.rr.rush.romans.entity.auth.AuthData
import com.rr.rush.romans.entity.auth.UserSignUpRequestDto
import com.rr.rush.romans.repository.AuthRepository
import com.ti89.dto.front.request.AuthDataDto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService {
    @Autowired(required = false)
    private lateinit var authRepository: AuthRepository

    fun loginAuth(authData: AuthDataDto): Boolean {
       return true
    }

    fun save(s: String) {
        authRepository.save(AuthData(UUID(10,10), s, "\$2a\$10\$Z57TDozA89ZMQpeMs.6QbOevx6JIa2wpgkbZkYVf5EE4EUbFnYEIS"));
        return
    }

    fun register(dto: UserSignUpRequestDto) {
        authRepository.save(AuthData.buildFromSignUp(dto))
    }
}