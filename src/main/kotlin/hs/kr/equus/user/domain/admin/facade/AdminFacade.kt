package hs.kr.equus.user.domain.admin.facade

import hs.kr.equus.user.domain.admin.domain.Admin
import hs.kr.equus.user.domain.admin.exception.AdminNotFoundException
import hs.kr.equus.user.domain.admin.domain.repository.AdminRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AdminFacade(
    private val adminRepository: AdminRepository
) {
    fun getCurrentUser(): Admin {
        val adminId = SecurityContextHolder.getContext().authentication.name
        return adminRepository.findByIdOrNull(adminId) ?: throw AdminNotFoundException
    }

    fun getUserById(adminId: String): Admin = adminRepository.findByIdOrNull(adminId) ?: throw AdminNotFoundException
}
