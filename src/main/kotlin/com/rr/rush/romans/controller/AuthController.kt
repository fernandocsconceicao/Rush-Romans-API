package com.rr.rush.romans.controller

import com.rr.rush.romans.entity.auth.UserSignUpRequestDto
import com.rr.rush.romans.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/signup")
    fun signUp (
        @RequestBody dto: UserSignUpRequestDto){
         authService.signUp(dto)
    }
    @PostMapping("/login")
    fun signIn (
        @RequestBody dto: UserSignUpRequestDto):Boolean{
        authService.login(dto)
    }

}
