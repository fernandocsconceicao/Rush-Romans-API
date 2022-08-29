package com.rr.rush.romans.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class WebSecurityConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/signup").permitAll()
            .anyRequest().authenticated()
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