package hs.kr.equus.user.domain.admin.domain.repository

import hs.kr.equus.user.domain.admin.domain.Admin
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AdminRepository : JpaRepository<Admin, UUID> {
    fun findByAdminId(adminId: String): Admin?
}
