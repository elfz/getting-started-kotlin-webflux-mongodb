package com.elfz.accountreactiveapi.controller

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class AccountService {

    private val webclient : WebClient = WebClient.create()

    fun create(partnerAccountId: String) : Mono<Account> =
        webclient
            .post()
            .uri("http://localhost:8080")
            .retrieve()
            .bodyToMono(Account::class.java)
}
