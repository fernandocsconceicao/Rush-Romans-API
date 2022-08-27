package com.yigitco.springsecurityclient.service

import com.yigitco.springsecurityclient.dto.PasswordResetDto
import com.yigitco.springsecurityclient.dto.UserSignupRequestDto
import com.yigitco.springsecurityclient.dto.UserSignupResponseDto
import com.yigitco.springsecurityclient.event.RegistrationCompleteEvent
import com.yigitco.springsecurityclient.model.PasswdResetToken
import com.yigitco.springsecurityclient.model.User
import com.yigitco.springsecurityclient.repository.PasswdResetTokenRepository
import com.yigitco.springsecurityclient.repository.UserRepository
import com.yigitco.springsecurityclient.util.UtilizationFunctions
import com.yigitco.springsecurityclient.util.mapper.UserSignupRequestMapper
import com.yigitco.springsecurityclient.util.mapper.UserSignupResponseMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userSignupRequestMapper: UserSignupRequestMapper,
    private val userSignupResponseMapper: UserSignupResponseMapper,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val utilizationFunctions: UtilizationFunctions,
    //a service should not dependent by another services's repository but in here use another repository for save time
    private val passwordResetTokenRepository: PasswdResetTokenRepository,
    private val passwordEncoder: PasswordEncoder
) {

    private val log = Logger.getLogger(UserService::class.java.name)


    fun registerUser(
        userSignupRequestDto: UserSignupRequestDto,
        httpServletRequest: HttpServletRequest
    ): UserSignupResponseDto {
        try {
            val requestUser = userSignupRequestMapper.mapToEntityFromDto(userSignupRequestDto)
            val savedUser = userRepository.save(requestUser)
            applicationEventPublisher.publishEvent(
                RegistrationCompleteEvent(
                    savedUser,
                    utilizationFunctions.getApplicationURL(httpServletRequest)
                )
            )
            return userSignupResponseMapper.mapToDto(savedUser)
        } catch (e: Exception) {
            throw Exception()
        }
    }


    fun updateUserEnabled(user: User) {
        val enableUserRequest = User(user.id, user.firstName, user.lastName, user.email, user.passwd, user.role, true)
        userRepository.save(enableUserRequest)
    }

    fun resetPassword(passwordResetDto: PasswordResetDto, httpServletRequest: HttpServletRequest) {
        val userResponse =
            userRepository.findUserByEmail(passwordResetDto.email).orElseThrow { Exception("User can Not Found") }
        val token = UUID.randomUUID().toString()
        createPasswordResetTokenForUser(userResponse!!, token, httpServletRequest)

    }

    private fun createPasswordResetTokenForUser(
        userResponse: User,
        token: String,
        httpServletRequest: HttpServletRequest
    ) {
        val passwordResetToken = PasswdResetToken(-1, token, LocalDateTime.now().plusMinutes(10), userResponse)
        passwordResetTokenRepository.save(passwordResetToken)
        val url =
            genereatePasswordResetURL(userResponse, token, utilizationFunctions.getApplicationURL(httpServletRequest))
        log.info("ResetPassword Mail Sended to : ${userResponse.email}")
        log.info("Click the link to reset your password $url")

    }

    private fun genereatePasswordResetURL(userResponse: User, token: String, applicationURL: String): String {
        return "$applicationURL/savePassword?token=$token"
    }

    fun savePassword(token: String, resetPasswordResetDto: PasswordResetDto): Boolean {
        val passwordResetToken = passwordResetTokenRepository.findPasswdResetTokenByToken(token)
            .orElseThrow { Exception("Token can not Found") }
        if (LocalDateTime.now().isBefore(passwordResetToken.expirationTime)) {
            val alreadySavedUser =
                userRepository.findUserByEmail(resetPasswordResetDto.email).orElseThrow { Exception() }
            val user = User(
                alreadySavedUser.id,
                alreadySavedUser.firstName,
                alreadySavedUser.lastName,
                alreadySavedUser.email,
                passwordEncoder.encode(resetPasswordResetDto.newPassword),
                alreadySavedUser.role,
                alreadySavedUser.enabled
            )
            userRepository.save(user)
            return true
        }
        return false
    }

}