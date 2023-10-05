package hs.kr.equus.user.domain.admin.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tbl_admin")
class Admin(
    @Id
    val id: String,

    @Column(name = "password", length = 60, nullable = false)
    val password: String
)
