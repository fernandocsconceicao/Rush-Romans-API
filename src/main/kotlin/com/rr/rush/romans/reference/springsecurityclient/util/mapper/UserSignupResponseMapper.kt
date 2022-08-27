package com.yigitco.springsecurityclient.util.mapper

import com.yigitco.springsecurityclient.dto.UserSignupResponseDto
import com.yigitco.springsecurityclient.model.User
import org.springframework.stereotype.Component

@Component
class UserSignupResponseMapper : Mapper<User,UserSignupResponseDto> {

    override fun mapToDto(entity: User): UserSignupResponseDto {
        return UserSignupResponseDto(entity.id,entity.firstName,entity.lastName,entity.email,entity.role,entity.enabled)
    }

    override fun mapToEntityFromDto(dto: UserSignupResponseDto): User {
        return User(-1,dto.firstName,dto.lastName,dto.email,"",dto.role,dto.enabled)
    }
}