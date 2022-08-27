package com.rr.rush.romans.controller

import com.rr.rush.romans.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @Autowired
    lateinit var authService: AuthService

    @GetMapping("home")
    fun test(){
         authService.save("test")
    }
}