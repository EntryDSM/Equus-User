package hs.kr.equus.user.domain.user.domain.repository

import hs.kr.equus.user.domain.user.domain.UserInfo
import org.springframework.data.repository.CrudRepository

interface UserInfoRepository: CrudRepository<UserInfo, String>
