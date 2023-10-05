package hs.kr.equus.user.global.security.auth

import hs.kr.equus.user.domain.admin.domain.Admin
import hs.kr.equus.user.domain.admin.facade.AdminFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AdminDetailsService(
    private val adminFacade: AdminFacade
) : UserDetailsService {
    override fun loadUserByUsername(adminId: String?): UserDetails {
        val admin: Admin? = adminId?.let { adminFacade.getUserById(it) }
        return AuthDetails(admin!!.id)
    }
}
