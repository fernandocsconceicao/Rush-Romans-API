package com.yigitco.springsecurityclient.util.mapper

import com.yigitco.springsecurityclient.dto.UserSignupRequestDto
import com.yigitco.springsecurityclient.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserSignupRequestMapper(private val passwordEncoder: PasswordEncoder) : Mapper<User, UserSignupRequestDto> {

    override fun mapToDto(entity: User): UserSignupRequestDto {
        return UserSignupRequestDto(entity.firstName, entity.lastName, entity.email, entity.passwd)
    }

    override fun mapToEntityFromDto(dto: UserSignupRequestDto): User {
        return User(-1, dto.firstName, dto.lastName, dto.email, passwordEncoder.encode(dto.passwd), "USER", false);
    }
}