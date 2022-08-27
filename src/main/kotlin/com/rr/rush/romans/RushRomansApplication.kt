package com.rr.rush.romans

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication

class RushRomansApplication

fun main(args: Array<String>) {
	runApplication<RushRomansApplication>(*args)
	println(BCryptPasswordEncoder().encode("123"))
}
