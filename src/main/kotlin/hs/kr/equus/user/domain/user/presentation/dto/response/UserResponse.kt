package hs.kr.equus.user.domain.user.presentation.dto.response

data class UserResponse(
    val name: String,
    val telephoneNumber: String,
    val isParent: Boolean
)
