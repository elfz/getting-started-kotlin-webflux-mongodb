package com.elfz.accountreactiveapi.controller

import com.elfz.accountreactiveapi.domain.Account
import com.elfz.accountreactiveapi.service.AccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/reactive")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/create")
    fun createAccount(@RequestBody account: AccountRequest): Mono<Account> =
        Mono.just(account)
            .flatMap { accountService.create(it) }

}
