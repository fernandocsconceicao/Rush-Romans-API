package com.rr.rush.romans.entity.auth

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Rule(
    @Id val id: Long, @Column private val authority: String, @ManyToOne val userData: AuthData
) : GrantedAuthority {

    override fun getAuthority(): String {
        return this.authority
    }
}