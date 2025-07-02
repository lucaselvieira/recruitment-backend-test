package br.com.recruitment.service.enrollment.controller.test

import br.com.recruitment.service.enrollment.application.usecase.student.GenerateStudentFinancialReportUsecase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class JobTestController(
    private val generateStudentFinancialReportUsecase: GenerateStudentFinancialReportUsecase
) {

    @GetMapping("/run-job")
    fun runJob(): ResponseEntity<String> {
        generateStudentFinancialReportUsecase.execute()
        return ResponseEntity.ok("Job executed manually")
    }
}
