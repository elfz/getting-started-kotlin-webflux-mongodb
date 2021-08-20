package com.elfz.accountreactiveapi.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/reactive")
class AccountController(
    private val accountReactiveRepository: AccountReactiveRepository,
    private val accountService: AccountService
) {

    @PostMapping("/create")
    fun createAccount(@RequestBody account: Account): Mono<Account> =
        Mono.just(account)
            .flatMap { accountReactiveRepository.findAccountByCustomerId(it.customerId) }
            .flatMap { accountService.create(it.partnerAccountId) }
}
