package hs.kr.equus.user.domain.auth.domain.repository

import hs.kr.equus.user.domain.auth.domain.PassInfo
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PassInfoRepository : CrudRepository<PassInfo, String> {
    fun findByPhoneNumber(phoneNumber: String): Optional<PassInfo>
}
