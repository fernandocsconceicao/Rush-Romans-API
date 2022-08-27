package com.yigitco.springsecurityclient.dto

data class PasswordResetDto(
    val email:String,
    val newPassword:String
)