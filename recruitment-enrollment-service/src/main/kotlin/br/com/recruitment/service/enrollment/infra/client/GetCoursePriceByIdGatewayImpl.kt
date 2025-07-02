package br.com.recruitment.service.enrollment.infra.client

import br.com.recruitment.service.enrollment.application.gateway.financial.course.GetCoursePriceByIdGateway
import br.com.recruitment.service.enrollment.infra.client.financial.dto.CoursePriceDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class GetCoursePriceByIdGatewayImpl(
    @Value("\${client.financial.url}")
    private val baseUrl: String,
) : AbstractWebClient(baseUrl), GetCoursePriceByIdGateway {

    private val client: WebClient = getClient()

    override fun execute(courseId: String): CoursePriceDto {
        val uri = "/api/v1/course-price/find-by-id/$courseId"

        return client.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(CoursePriceDto::class.java)
            .block() ?: throw RuntimeException("Course price not found")
    }
}
