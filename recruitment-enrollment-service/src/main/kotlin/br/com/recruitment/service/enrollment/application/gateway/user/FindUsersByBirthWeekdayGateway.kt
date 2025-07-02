package br.com.recruitment.service.enrollment.application.gateway.user

import br.com.recruitment.service.enrollment.domain.user.User
import java.time.DayOfWeek

interface FindUsersByBirthWeekdayGateway {
    fun findByBirthWeekday(weekday: DayOfWeek): List<User>
}
