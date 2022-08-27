package com.rr.rush.romans.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class WebSecurityConfiguration {

    @Bean
     fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST,"/register").permitAll()
            .and()
            .csrf().disable()
            .formLogin().and().httpBasic()

        return http.build()
    }


    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}