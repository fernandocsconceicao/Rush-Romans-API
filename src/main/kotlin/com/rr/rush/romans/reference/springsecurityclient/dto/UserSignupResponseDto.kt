package com.yigitco.springsecurityclient.dto

data class UserSignupResponseDto(

    val id: Long,

    val firstName: String,

    val lastName: String,

    val email: String,

    val role: String,

    val enabled: Boolean,
)
