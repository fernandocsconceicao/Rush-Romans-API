package com.rr.rush.romans.repository

import com.rr.rush.romans.entity.auth.AuthData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository

 interface AuthRepository : JpaRepository<AuthData, UUID> {
    fun findPasswordByUsername(username: String): String?
}
