package hs.kr.equus.user.domain.admin.presentation.dto.request

import javax.validation.constraints.NotBlank

data class AdminLoginRequest(
    @NotBlank
    val id: String,

    @NotBlank
    val password: String
)
