package com.yigitco.springsecurityclient.controller

import com.yigitco.springsecurityclient.dto.PasswordResetDto
import com.yigitco.springsecurityclient.dto.UserSignupRequestDto
import com.yigitco.springsecurityclient.service.UserService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userSignupRequestDto: UserSignupRequestDto,httpServletRequest: HttpServletRequest): String {
        userService.registerUser(userSignupRequestDto,httpServletRequest)
        return "Success"
    }

    @GetMapping("/resetPassword")
    fun resetPassword(@RequestBody passwordResetDto: PasswordResetDto,httpServletRequest: HttpServletRequest){
        userService.resetPassword(passwordResetDto,httpServletRequest)
    }

    @GetMapping("/savePassword")
    fun savePassword(@RequestParam("token") token:String,@RequestBody resetPasswordResetDto: PasswordResetDto) : String{
        val isUpdatedPassword = userService.savePassword(token,resetPasswordResetDto)
        return if(isUpdatedPassword) "Password reseted" else "Invalid Reset Operation"
    }

    @GetMapping("/hi")
    fun sayHi() : String{
        return "Hi :)"
    }

}