package com.yigitco.springsecurityclient.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .antMatchers(
                "/register",
                "/resendVerifyToken*",
                "/verifyRegistration"
            )
            .permitAll()

        return httpSecurity.build();
    }


    companion object {
        val WHITELIST_URLS = ArrayList<String>();
    }
}