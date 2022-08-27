package com.yigitco.springsecurityclient.service

import com.yigitco.springsecurityclient.model.User
import com.yigitco.springsecurityclient.model.VerificationToken
import com.yigitco.springsecurityclient.repository.VerificationTokenRepository
import com.yigitco.springsecurityclient.util.UtilizationFunctions
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest

@Service
class VerificationTokenService(
    private val verificationTokenRepository: VerificationTokenRepository,
    private val userService: UserService,
    private val utilizationFunctions: UtilizationFunctions
) {
    private val log = Logger.getLogger(VerificationTokenService::class.java.name)

    fun saveVerificationTokenForUser(user: User, token: String) {
        val verificationToken = VerificationToken(-1, token, LocalDateTime.now().plusMinutes(10), user)
        verificationTokenRepository.save(verificationToken)
    }

    fun verifyRegistrationToken(token: String): Boolean {
        val verificationToken: VerificationToken =
            verificationTokenRepository.findByToken(token).orElseThrow { Exception("Token Can Not Found") }
        if (verificationToken.expirationTime.isAfter(LocalDateTime.now())) {
            userService.updateUserEnabled(verificationToken.user)
            return true
        }
        return false
    }

    fun generateNewVerificationToken(oldToken: String): VerificationToken {
        val oldTokenResponse: VerificationToken =
            verificationTokenRepository.findByToken(oldToken).orElseThrow { Exception("Token Can Not Found") }
        val newVerificationToken = VerificationToken(
            oldTokenResponse.id,
            UUID.randomUUID().toString(),
            oldTokenResponse.expirationTime.plusMinutes(10), oldTokenResponse.user
        )
        verificationTokenRepository.save(newVerificationToken)
        return newVerificationToken
    }

    fun resendVerificationTokenMail(token: String, httpServletRequest: HttpServletRequest) {
        val generatedNewVerificationToken = generateNewVerificationToken(token)
        val user = generatedNewVerificationToken.user
        val generatedVerificationURL = generateVerificationURL(
            generatedNewVerificationToken.token,
            utilizationFunctions.getApplicationURL(httpServletRequest)
        )

        sendVerificationTokenMail(user, generatedVerificationURL)
    }

    fun sendVerificationTokenMail(user: User, url: String) {
        // TODO: 10.08.22 Should be add real mail sending system
        log.info("Verification Token Mail Sended to : ${user.email}")
        log.info("Click the link to verify your account $url")
    }


    fun generateVerificationURL(token: String, applicationURL: String): String {
        return "$applicationURL/verifyRegistration?token=$token";
    }
}