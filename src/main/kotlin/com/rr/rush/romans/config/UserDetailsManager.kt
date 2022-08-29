package com.rr.rush.romans.config

import com.rr.rush.romans.repository.AuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsService() : UserDetailsService{
    @Autowired
    lateinit var authRepository: AuthRepository

    @Autowired
    lateinit var

    override fun loadUserByUsername(username: String?): UserDetails {
        val user= authRepository.findByUsername(username!!)
        if(user == null){
            throw Exception("Usuario não encontrado")
        }
        return user.get()
    }
}