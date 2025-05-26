package br.com.recruitment.service.enrollment.domain.enrollment

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "enrollment")
class Enrollment(
    @Column(length = 36)
    var coursePriceId: String,

    @Column(length = 36)
    var userId: String,

    var duration: Int,
) {
    @Id
    @Column(length = 36)
    var id: String = UUID.randomUUID().toString()

    constructor() : this("", "", 0)
}
