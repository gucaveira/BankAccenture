package com.bankaccenture.repository

import androidx.lifecycle.LiveData
import com.bankaccenture.model.Transaction
import com.bankaccenture.retrofit.webclient.WebClient

class HomeRepository(private val webClient: WebClient) {

    fun allTransaction(userId: Int): LiveData<List<Transaction>?> {
        return webClient.allTransactions(userId)
    }
}