package hs.kr.equus.user.global.security.auth

import hs.kr.equus.user.domain.user.domain.User
import hs.kr.equus.user.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(phoneNumber: String?): UserDetails {
        val user: User? = phoneNumber?.let { userFacade.getUserByPhoneNumber(it) }
        return AuthDetails(user!!.phoneNumber)
    }
}
