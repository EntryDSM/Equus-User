package hs.kr.equus.user.domain.auth.presentation.dto.resopnse

data class QueryPassInfoResponse(
    private val phoneNumber: String,
    private val name: String
)