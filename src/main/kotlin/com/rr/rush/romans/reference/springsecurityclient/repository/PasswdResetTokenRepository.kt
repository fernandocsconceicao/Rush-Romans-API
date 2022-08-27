package com.yigitco.springsecurityclient.repository

import com.yigitco.springsecurityclient.model.PasswdResetToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PasswdResetTokenRepository : JpaRepository<PasswdResetToken,Long>{
    fun findPasswdResetTokenByToken(token:String) : Optional<PasswdResetToken>
}