package hs.kr.equus.user.domain.auth.entity.repository

import hs.kr.equus.user.domain.auth.entity.PassInfo
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface PassInfoRepository: CrudRepository<PassInfo, String> {

    fun findByPhoneNumber(phoneNumber: String): PassInfo
}