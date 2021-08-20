package com.elfz.partneraccountapi

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountRepository: AccountRepository
) {

    @GetMapping
    fun getAllAccounts(): ResponseEntity<List<Account>> {
        val accounts = accountRepository.findAll()
        return ResponseEntity.ok(accounts)
    }

    @PostMapping
    fun createAccount(@RequestBody account: Account): ResponseEntity<Account> {
        val accountCreated = accountRepository.save(account)
        return ResponseEntity.ok(accountCreated)
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable("id") id: String): ResponseEntity<Account> {
        val account = accountRepository.findOneById(id)
        return ResponseEntity.ok(account)
    }

}