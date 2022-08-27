package com.yigitco.springsecurityclient.event.listener

import com.yigitco.springsecurityclient.event.RegistrationCompleteEvent
import com.yigitco.springsecurityclient.service.VerificationTokenService
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.util.*
import java.util.logging.Logger

@Component
class RegistrationCompleteListener(
    private val verificationTokenService: VerificationTokenService,
    ) : ApplicationListener<RegistrationCompleteEvent> {

    private val log = Logger.getLogger(RegistrationCompleteListener::class.java.name)

    override fun onApplicationEvent(event: RegistrationCompleteEvent) {
        //Create the verification Token For Verify User With Link.
        val user = event.user
        val token = UUID.randomUUID().toString()
        verificationTokenService.saveVerificationTokenForUser(user, token)

        //Send Mail To User
        val url = verificationTokenService.generateVerificationURL(token,event.applicationURL)
        verificationTokenService.sendVerificationTokenMail(user,url)
    }
}