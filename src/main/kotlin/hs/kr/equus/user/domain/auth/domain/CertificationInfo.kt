package hs.kr.equus.user.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash
class CertificationInfo(
    @Id
    val phoneNumber: String,

    val certificationNumber: Int,

    var isValid: Boolean,

    @TimeToLive
    var ttl: Long
) {
    fun confirmCertification() {
        this.isValid = true
    }
}
