package br.com.recruitment.service.financial.application.gateway.instalment

import br.com.recruitment.service.financial.domain.instalment.Instalment

interface FindInstalmentByEnrollmentIdGateway {
    fun execute(enrollmentId: String): List<Instalment>
}
