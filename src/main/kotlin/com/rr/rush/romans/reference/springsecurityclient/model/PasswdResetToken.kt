package com.yigitco.springsecurityclient.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class PasswdResetToken(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val token: String,

    val expirationTime: LocalDateTime,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
    val user: User
)