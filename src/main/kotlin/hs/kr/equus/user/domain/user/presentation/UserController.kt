package hs.kr.equus.user.domain.user.presentation

import hs.kr.equus.user.domain.user.presentation.dto.request.UserLoginRequest
import hs.kr.equus.user.domain.user.presentation.dto.request.UserSignupRequest
import hs.kr.equus.user.domain.user.service.UserLoginService
import hs.kr.equus.user.domain.user.service.UserSignupService
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/user")
@RestController
class UserController(
    private val userSignupService: UserSignupService,
    private val userLoginService: UserLoginService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @RequestBody @Valid
        userSignupRequest: UserSignupRequest
    ): TokenResponse =
        userSignupService.execute(userSignupRequest)

    @PostMapping("/auth")
    fun login(
        @RequestBody @Valid
        userLoginRequest: UserLoginRequest
    ): TokenResponse =
        userLoginService.execute(userLoginRequest)
}
