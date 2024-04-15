package hs.kr.equus.user.domain.user.service

import hs.kr.equus.user.domain.user.facade.UserFacade
import hs.kr.equus.user.domain.user.presentation.dto.response.UserResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class QueryUserInfoService(
    private val userFacade: UserFacade
) {
    fun execute(): UserResponse {
        val user = userFacade.getCurrentUser()

        return user.run {
            UserResponse(
                name = name,
                telephoneNumber = phoneNumber,
                isParent = isParent
            )
        }
    }
}
