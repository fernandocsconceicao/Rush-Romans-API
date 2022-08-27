package com.yigitco.springsecurityclient.model

import javax.persistence.*

@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    val id:Long,

    val firstName:String,

    val lastName:String,

    @Column(unique = true)
    val email:String,

    @Column(length = 60)
    val passwd:String,

    val role:String,

    val enabled:Boolean,
)