package hs.kr.equus.user.domain.auth.presentation.dto.request

data class CheckCertificationInfoRequest(
    val certificationNumber: Int,
    val phoneNumber: String
)
