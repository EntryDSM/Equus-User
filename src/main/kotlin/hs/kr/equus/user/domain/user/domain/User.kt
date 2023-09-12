package hs.kr.equus.user.domain.user.domain

import hs.kr.equus.user.domain.BaseTimeEntity
import hs.kr.equus.user.domain.BaseUUIDEntity
import java.util.*
import javax.persistence.*

@Entity(name = "tbl_user")
 class User(
    id: UUID?,

    @Column(columnDefinition = "char(11)", nullable = false, unique = true)
    val telephoneNumber: String,

    @Column(columnDefinition = "char(60)", nullable = false)
    val password: String,

    @Column(columnDefinition = "char(5)", nullable = false)
    val name: String,

    @Column(columnDefinition = "bit(1) default 1", nullable = false)
    val isStudent: Boolean,

    @Column(name = "entry_info_id", nullable = true)
    val entryInfoId: Long?
) : BaseUUIDEntity(id)
