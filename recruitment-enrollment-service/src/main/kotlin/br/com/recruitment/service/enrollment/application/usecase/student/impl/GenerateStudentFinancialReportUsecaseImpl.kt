package br.com.recruitment.service.enrollment.application.usecase.student.impl

import br.com.recruitment.service.enrollment.application.gateway.user.FindUsersByBirthWeekdayGateway
import br.com.recruitment.service.enrollment.application.usecase.enrollment.FindEnrollmentByUserIdUsecase
import br.com.recruitment.service.enrollment.application.gateway.financial.instalment.FindInstalmentsIdsByEnrollmentId
import br.com.recruitment.service.enrollment.application.gateway.financial.instalment.GetInstalmentById
import br.com.recruitment.service.enrollment.application.usecase.student.GenerateStudentFinancialReportUsecase
import br.com.recruitment.service.enrollment.application.gateway.financial.course.GetCoursePriceByIdGateway
import java.math.BigDecimal
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class GenerateStudentFinancialReportUsecaseImpl(
    private val findEnrollmentByUserIdUsecase: FindEnrollmentByUserIdUsecase,
    private val findUsersByBirthWeekdayGateway: FindUsersByBirthWeekdayGateway,
    private val findInstalmentsIdsByEnrollmentId: FindInstalmentsIdsByEnrollmentId,  //gateway??
    private val getInstalmentById: GetInstalmentById,
    private val getCoursePriceByIdGateway: GetCoursePriceByIdGateway
) : GenerateStudentFinancialReportUsecase {


    private val logger = LoggerFactory.getLogger(GenerateStudentFinancialReportUsecaseImpl::class.java)

    override fun execute() {
        val today = LocalDate.now()
        val weekday = today.dayOfWeek

        logger.info("Generating student financial report for weekday: $weekday")

        val users = findUsersByBirthWeekdayGateway.findByBirthWeekday(weekday)

        logger.info("Found ${users.size} user(s) born on $weekday")

        users.forEach { user ->
            val enrollments = findEnrollmentByUserIdUsecase.execute(user.id)
            logger.info("User ${user.fullName} has ${enrollments.size} enrollment(s)")

            enrollments.forEach { enrollment ->
            val instalmentIds = findInstalmentsIdsByEnrollmentId.execute(enrollment.id)

            val paidAmount = instalmentIds.mapNotNull { instalmentId ->
                val instalment = getInstalmentById.execute(instalmentId)
                if (instalment.status.name == "PAID") instalment.paidAmount else null
            }.fold(BigDecimal.ZERO) { acc, value -> acc + value }

            logger.info("Enrollment ${enrollment.id} paid amount: $paidAmount")

            val coursePrice = getCoursePriceByIdGateway.execute(enrollment.coursePriceId)

            val expectedTotal = coursePrice.price.multiply(BigDecimal(enrollment.duration))
            val remaining = expectedTotal.subtract(paidAmount)

            logger.info("User: ${user.fullName}, Course: ${enrollment.coursePriceId}, Paid: $paidAmount, Expected: $expectedTotal, Remaining: $remaining")

            }
        }
    }
}
