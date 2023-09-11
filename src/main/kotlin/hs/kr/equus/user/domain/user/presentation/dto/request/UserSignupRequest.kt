package hs.kr.equus.user.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class UserSignupRequest(
    @NotBlank(message = "telephone_number은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    val telephoneNumber: String,

    @NotBlank(message = "password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Pattern(
        regexp =
        "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;<=>?@＼^_`{|}~]{8,32}$",
        message = "password는 소문자, 숫자, 특수문자가 포함되어야 합니다."
    )
    val password: String,

    @NotNull
    val isStudent: Boolean
)
