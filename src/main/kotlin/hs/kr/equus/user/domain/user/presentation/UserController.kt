package hs.kr.equus.user.domain.user.presentation

import hs.kr.equus.user.domain.user.presentation.dto.request.ChangePasswordRequest
import hs.kr.equus.user.domain.user.presentation.dto.request.UserLoginRequest
import hs.kr.equus.user.domain.user.presentation.dto.request.UserSignupRequest
import hs.kr.equus.user.domain.user.presentation.dto.response.InternalUserResponse
import hs.kr.equus.user.domain.user.presentation.dto.response.UserResponse
import hs.kr.equus.user.domain.user.service.*
import hs.kr.equus.user.global.utils.token.dto.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RequestMapping("/user")
@RestController
class UserController(
    private val userSignupService: UserSignupService,
    private val userLoginService: UserLoginService,
    private val changePasswordService: ChangePasswordService,
    private val userTokenRefreshService: UserTokenRefreshService,
    private val userWithdrawalService: UserWithdrawalService,
    private val queryUserByUUIDService: QueryUserByUUIDService,
    private val queryUserInfoService: QueryUserInfoService
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

    @PatchMapping("/password")
    fun changePassword(
        @RequestBody @Valid
        changePasswordRequest: ChangePasswordRequest
    ) =
        changePasswordService.execute(changePasswordRequest)

    @PutMapping("/auth")
    fun tokenRefresh(@RequestHeader("X-Refresh-Token") refreshToken: String): TokenResponse =
        userTokenRefreshService.execute(refreshToken)

    @DeleteMapping
    fun withdrawal() = userWithdrawalService.execute()

    @GetMapping("/{userId}")
    fun findUserByUUID(@PathVariable userId: UUID): InternalUserResponse {
        return queryUserByUUIDService.execute(userId)
    }

    @GetMapping("/info")
    fun getUserInfo(): UserResponse = queryUserInfoService.execute()
}
