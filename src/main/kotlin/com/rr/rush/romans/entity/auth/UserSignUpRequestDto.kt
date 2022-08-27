package com.rr.rush.romans.entity.auth

import javax.validation.constraints.Email

data class UserSignUpRequestDto(
    val username:String,
    val password: String,
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    val email: String)