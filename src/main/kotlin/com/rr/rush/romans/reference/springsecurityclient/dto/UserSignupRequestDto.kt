package com.yigitco.springsecurityclient.dto

data class UserSignupRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val passwd: String,
)