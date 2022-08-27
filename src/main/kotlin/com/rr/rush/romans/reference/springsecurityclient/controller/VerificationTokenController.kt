package com.yigitco.springsecurityclient.controller

import com.yigitco.springsecurityclient.service.VerificationTokenService
import com.yigitco.springsecurityclient.util.UtilizationFunctions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class VerificationTokenController(
    private val verificationTokenService: VerificationTokenService,
    private val utilizationFunctions: UtilizationFunctions
) {

    @GetMapping("/verifyRegistration")
    fun verifyRegistrationToken(@RequestParam("token") token: String): String {
        val isVerified = verificationTokenService.verifyRegistrationToken(token)
        return if (isVerified) "Verified" else "Not Verified"
    }

    @GetMapping("/resendVerifyToken")
    fun resendVerificationToken(@RequestParam("token") token: String, httpServletRequest: HttpServletRequest): String {
        verificationTokenService.resendVerificationTokenMail(token,httpServletRequest)
        return "Sended"
    }
}