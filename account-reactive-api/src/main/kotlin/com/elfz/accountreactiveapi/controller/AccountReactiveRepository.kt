package com.elfz.accountreactiveapi.controller

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface AccountReactiveRepository : ReactiveMongoRepository<Account, String> {

    fun findAccountByCustomerId(customerId: String): Mono<Account>
}
