package hs.kr.equus.user.domain.user.domain.repository

import hs.kr.equus.user.domain.user.domain.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserJpaEntity, UUID> {
    fun findByPhoneNumber(phoneNumber: String): Optional<UserJpaEntity>

    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun deleteById(id: UUID?)
}
