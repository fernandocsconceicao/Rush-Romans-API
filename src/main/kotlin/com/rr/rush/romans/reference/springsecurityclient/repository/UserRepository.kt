package com.yigitco.springsecurityclient.repository

import com.yigitco.springsecurityclient.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User,Long> {
    fun findUserByEmail(email:String) : Optional<User>
}