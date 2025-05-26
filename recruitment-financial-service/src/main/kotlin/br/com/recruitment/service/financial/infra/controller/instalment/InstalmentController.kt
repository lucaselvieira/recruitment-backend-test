package br.com.recruitment.service.financial.infra.controller.instalment

import br.com.recruitment.service.financial.application.usecase.instalment.CreateOrUpdateInstallmentUsecase
import br.com.recruitment.service.financial.application.usecase.instalment.GetInstalmentByEnrollmentIdUsecase
import br.com.recruitment.service.financial.domain.instalment.Instalment
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/instalment")
class InstalmentController(
    private val createOrUpdateInstallmentUseCase: CreateOrUpdateInstallmentUsecase,
    private val getInstalmentByEnrollmentIdUsecase: GetInstalmentByEnrollmentIdUsecase,
) {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstalment(
        @RequestBody(required = true) instalment: Instalment,
    ): Instalment {
        return createOrUpdateInstallmentUseCase.execute(instalment)
    }

    @GetMapping("/find-by-enrollment-id/{enrollmentId}")
    fun findByEnrollmentId(
        @PathVariable enrollmentId: String,
    ): List<Instalment> {
        return getInstalmentByEnrollmentIdUsecase.execute(enrollmentId)
    }
}
