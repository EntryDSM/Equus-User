package hs.kr.equus.user.domain.user.domain

import hs.kr.equus.user.domain.BaseUUIDEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_user")
class UserJpaEntity(
    id: UUID?,

    @Column(columnDefinition = "char(11)", nullable = false, unique = true)
    val phoneNumber: String,

    @Column(columnDefinition = "char(60)", nullable = false)
    var password: String,

    @Column(columnDefinition = "char(5)", nullable = false)
    val name: String,

    @Column(columnDefinition = "bit(1) default 1", nullable = false)
    val isStudent: Boolean,

    @Column(name = "receipt_code", nullable = true)
    val receiptCode: UUID?
) : BaseUUIDEntity(id) {
    fun changePassword(password: String) {
        this.password = password
    }
}
