package com.elfz.accountreactiveapi.service

import com.elfz.accountreactiveapi.controller.AccountRequest
import com.elfz.accountreactiveapi.domain.Account
import com.elfz.accountreactiveapi.repository.AccountReactiveRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class AccountService(
    val accountReactiveRepository: AccountReactiveRepository
) {

    private val webclient : WebClient = WebClient.create()

    fun create(accountRequest: AccountRequest) =
        Mono.just(buildPartnerAccountRequest())
            .flatMap { callPartner(it) }
            .flatMap { buildAccount(it, accountRequest.clientId) }
            .flatMap { accountReactiveRepository.save(it) }


    private fun callPartner(request: AccountPartnerRequest) = webclient
        .post()
        .uri("http://localhost:8082/accounts")
        .body(Mono.just(request), AccountPartnerRequest::class.java)
        .retrieve()
        .bodyToMono(AccountPartnerResponse::class.java)


    private fun buildPartnerAccountRequest() = AccountPartnerRequest(
        name = "Elfz name",
        description = "Elfz description",
        product = "123456"
    )

    private fun buildAccount(partnerResponse: AccountPartnerResponse, clientId: String) =
        Mono.just(partnerResponse)
            .map { Account(
                clientId = clientId,
                partnerId = it.id,
                product = it.product
            ) }
}
