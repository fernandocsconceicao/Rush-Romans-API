package com.rr.rush.romans.entity.auth

import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_tb")
class AuthData(
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    var id: UUID?,
    @Column(nullable = false, unique = true)
    private var username: String,
    @Column(nullable = false)
    private var password: String
) : UserDetails, Serializable {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }


    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        private val bCryptPasswordEncoder= BCryptPasswordEncoder()

            fun buildFromSignUp(dto: UserSignUpRequestDto):AuthData{
                return AuthData(null,dto.username, bCryptPasswordEncoder.encode(dto.password))
            }
        private const val serialVersionUID = 1L
    }
}