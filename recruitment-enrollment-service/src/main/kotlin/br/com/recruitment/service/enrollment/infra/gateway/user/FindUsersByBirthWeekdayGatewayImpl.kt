package br.com.recruitment.service.enrollment.infra.gateway.user

import br.com.recruitment.service.enrollment.application.gateway.user.FindUsersByBirthWeekdayGateway
import br.com.recruitment.service.enrollment.domain.user.User
import br.com.recruitment.service.enrollment.infra.repository.user.UserRepository
import org.springframework.stereotype.Component
import java.time.DayOfWeek

@Component
class FindUsersByBirthWeekdayGatewayImpl(
    private val userRepository: UserRepository
) : FindUsersByBirthWeekdayGateway {

    override fun findByBirthWeekday(weekday: DayOfWeek): List<User> {
        return userRepository.findAll().filter {
            it.birthDate.dayOfWeek == weekday
        }
    }
}
