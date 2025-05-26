package br.com.recruitment.service.financial.application.usecase.instalment

import br.com.recruitment.service.financial.domain.instalment.Instalment

fun interface GetInstalmentByEnrollmentIdUsecase {
    fun execute(enrollmentId: String): List<Instalment>
}
