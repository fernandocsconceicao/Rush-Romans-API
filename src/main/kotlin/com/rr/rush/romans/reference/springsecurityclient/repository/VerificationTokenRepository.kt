package com.yigitco.springsecurityclient.repository

import com.yigitco.springsecurityclient.model.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VerificationTokenRepository : JpaRepository<VerificationToken, Long> {
    fun findByToken(token: String) : Optional<VerificationToken>

}