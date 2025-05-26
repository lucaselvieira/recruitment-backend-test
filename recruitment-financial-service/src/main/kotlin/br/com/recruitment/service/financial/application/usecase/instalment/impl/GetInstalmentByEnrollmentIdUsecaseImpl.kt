package br.com.recruitment.service.financial.application.usecase.instalment.impl

import br.com.recruitment.service.financial.application.gateway.instalment.FindInstalmentByEnrollmentIdGateway
import br.com.recruitment.service.financial.application.usecase.instalment.GetInstalmentByEnrollmentIdUsecase
import br.com.recruitment.service.financial.domain.instalment.Instalment
import br.com.recruitment.service.financial.loggerFor
import org.springframework.stereotype.Service

@Service
class GetInstalmentByEnrollmentIdUsecaseImpl(
    private val findInstalmentByEnrollmentIdGateway: FindInstalmentByEnrollmentIdGateway,
) : GetInstalmentByEnrollmentIdUsecase {
    private val log = loggerFor<GetInstalmentByEnrollmentIdUsecaseImpl>()

    override fun execute(enrollmentId: String): List<Instalment> {
        return findInstalmentByEnrollmentIdGateway.execute(enrollmentId)
    }
}
