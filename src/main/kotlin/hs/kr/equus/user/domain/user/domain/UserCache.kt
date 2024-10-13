package hs.kr.equus.user.domain.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.*

@RedisHash(value = "user_cache")
data class UserCache (
    @Id
    val id: UUID?,
    val phoneNumber: String,
    val name: String,
    val isParent: Boolean,
    val receiptCode: Long?,
    val role: UserRole,
    @TimeToLive
    val ttl: Long
)
