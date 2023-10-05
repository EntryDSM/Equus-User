package hs.kr.equus.user.domain.admin.domain

import hs.kr.equus.user.domain.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tbl_admin")
class Admin(
    id: UUID?,

    @Column(name = "admin_id", length = 15, nullable = false)
    val adminId: String,

    @Column(name = "password", length = 60, nullable = false)
    val password: String
) : BaseUUIDEntity(id)
