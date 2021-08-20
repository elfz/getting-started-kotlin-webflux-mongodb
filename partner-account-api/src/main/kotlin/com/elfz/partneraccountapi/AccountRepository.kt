package com.elfz.partneraccountapi

import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository : MongoRepository<Account, String> {
    fun findOneById(id: String): Account
    override fun deleteAll()

}