package com.rr.rush.romans.service

import com.rr.rush.romans.entity.auth.AuthData
import com.rr.rush.romans.entity.auth.UserSignUpRequestDto
import com.rr.rush.romans.repository.AuthRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {
    @Autowired()
    private lateinit var authRepository: AuthRepository

    fun signUp(dto: UserSignUpRequestDto) {
        authRepository.save(AuthData.buildFromSignUp(dto))
    }

    fun login(dto: UserSignUpRequestDto):Boolean {
        val user = authRepository.findByUsername(dto.username)
        if( user.isPresent){
            return (user.get().password == BCryptPasswordEncoder().encode(dto.password))
        }
        return false
    }
}