package hs.kr.equus.user.domain.admin.domain.repository

import hs.kr.equus.user.domain.admin.domain.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, String>
