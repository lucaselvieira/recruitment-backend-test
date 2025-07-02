package br.com.recruitment.service.enrollment.application.gateway.financial.course

import br.com.recruitment.service.enrollment.infra.client.financial.dto.CoursePriceDto

fun interface GetCoursePriceByIdGateway {
    fun execute(courseId: String): CoursePriceDto
}
