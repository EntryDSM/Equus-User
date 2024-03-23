package hs.kr.equus.user.domain.auth.presentation.dto.request

import javax.validation.constraints.NotBlank

data class PassPopupRequest(
    @NotBlank(message = "redirect_url은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    val redirectUrl: String
)
