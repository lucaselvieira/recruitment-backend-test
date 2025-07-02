package br.com.recruitment.service.enrollment.scheduler

import br.com.recruitment.service.enrollment.application.usecase.student.GenerateStudentFinancialReportUsecase
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class StudentFinancialReportJob(
    private val generateStudentFinancialReportUsecase: GenerateStudentFinancialReportUsecase
) {
    private val logger = LoggerFactory.getLogger(StudentFinancialReportJob::class.java)

    // Runs every day at 3 AM
    @Scheduled(cron = "0 0 3 * * *")
    fun execute() {
        logger.info("Starting scheduled student financial report job")
        generateStudentFinancialReportUsecase.execute()
        logger.info("Student financial report job finished")
    }
}
