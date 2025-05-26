package br.com.recruitment.service.financial.infra.repository.instalment

import br.com.recruitment.service.financial.application.gateway.instalment.FindInstalmentByEnrollmentIdGateway
import br.com.recruitment.service.financial.domain.instalment.Instalment
import org.springframework.stereotype.Component

@Component
class FindInstalmentByEnrollmentIdGatewayImpl(
    private val instalmentRepository: InstalmentRepository,
) : FindInstalmentByEnrollmentIdGateway {
    override fun execute(enrollmentId: String): List<Instalment> = instalmentRepository.findByEnrollmentId(enrollmentId)
}
