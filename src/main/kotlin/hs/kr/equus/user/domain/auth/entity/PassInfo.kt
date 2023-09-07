package hs.kr.equus.user.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
data class PassInfo(
    @Id
    val name: String,
    @Indexed
    val phoneNumber: String,
    @TimeToLive
    val ttl: Long
)
