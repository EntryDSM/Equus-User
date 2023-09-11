package hs.kr.equus.user.domain.user.presentation

import hs.kr.equus.user.domain.user.presentation.dto.request.UserSignupRequest
import hs.kr.equus.user.domain.user.service.UserSignupService
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val userSignupService: UserSignupService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody userSignupRequest: UserSignupRequest): TokenResponse {
        return userSignupService.execute(userSignupRequest)
    }
}
