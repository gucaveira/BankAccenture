package com.bankaccenture.retrofit.webclient

import com.bankaccenture.model.Transaction
import com.google.gson.annotations.SerializedName

data class ResponseTransactions(

    @field:SerializedName("statementList")
    val transactionsList: List<Transaction>? = null,

    @field:SerializedName("error")
    val error: Error? = null
)