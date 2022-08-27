package com.yigitco.springsecurityclient.event

import com.yigitco.springsecurityclient.model.User
import org.springframework.context.ApplicationEvent

class RegistrationCompleteEvent(val user: User, val applicationURL: String) : ApplicationEvent(user) {


}