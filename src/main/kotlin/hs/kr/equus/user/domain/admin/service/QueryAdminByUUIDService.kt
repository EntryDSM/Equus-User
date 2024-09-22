package hs.kr.equus.user.domain.admin.service

import hs.kr.equus.user.domain.admin.domain.repository.AdminRepository
import hs.kr.equus.user.domain.admin.exception.AdminNotFoundException
import hs.kr.equus.user.domain.admin.presentation.dto.response.InternalAdminResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryAdminByUUIDService(
    private val adminRepository: AdminRepository
) {

    @Transactional(readOnly = true)
    fun execute(adminId: UUID): InternalAdminResponse {
        val admin = adminRepository.findByIdOrNull(adminId) ?: throw AdminNotFoundException
        return InternalAdminResponse(
            id = admin.id!!
        )
    }
}
