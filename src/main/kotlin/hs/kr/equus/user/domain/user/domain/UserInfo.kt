package hs.kr.equus.user.domain.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
class UserInfo(
    @Id
    val token: String,
    @Indexed
    val userId: String,
    @Indexed
    val userRole: String,
    @TimeToLive
    val ttl: Long
)
