package hs.kr.equus.user.domain.auth.domain.repository

import hs.kr.equus.user.domain.auth.domain.CertificationInfo
import org.springframework.data.repository.CrudRepository

interface CertificationInfoRepository : CrudRepository<CertificationInfo, String> {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
}
