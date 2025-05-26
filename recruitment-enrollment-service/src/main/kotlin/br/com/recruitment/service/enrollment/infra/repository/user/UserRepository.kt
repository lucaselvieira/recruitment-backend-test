package br.com.recruitment.service.enrollment.infra.repository.user

import br.com.recruitment.service.enrollment.domain.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository : MongoRepository<User, String> {
    @Query("{'document': ?0 }")
    fun findByDocument(document: String): User?
}
