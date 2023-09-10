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
    override fun loadUserByUsername(telephoneNumber: String?): UserDetails {
        val user: User? = telephoneNumber?.let { userFacade.getUserByTelephoneNumber(it) }
        return AuthDetails(user!!.telephoneNumber)
    }
}
