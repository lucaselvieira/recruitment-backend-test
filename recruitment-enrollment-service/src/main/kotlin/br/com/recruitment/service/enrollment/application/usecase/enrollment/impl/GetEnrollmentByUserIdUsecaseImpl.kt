package br.com.recruitment.service.enrollment.application.usecase.enrollment.impl

import br.com.recruitment.service.enrollment.application.gateway.enrollment.FindEnrollmentByUserIdGateway
import br.com.recruitment.service.enrollment.application.usecase.enrollment.GetEnrollmentByUserIdUsecase
import br.com.recruitment.service.enrollment.domain.enrollment.Enrollment
import br.com.recruitment.service.enrollment.loggerFor
import org.springframework.stereotype.Service

@Service
class GetEnrollmentByUserIdUsecaseImpl(
    private val findEnrollmentByUserIdGateway: FindEnrollmentByUserIdGateway,
) : GetEnrollmentByUserIdUsecase {
    private val log = loggerFor<GetEnrollmentByUserIdUsecaseImpl>()

    override fun execute(userId: String): List<Enrollment> {
        return findEnrollmentByUserIdGateway.execute(userId)
    }
}
