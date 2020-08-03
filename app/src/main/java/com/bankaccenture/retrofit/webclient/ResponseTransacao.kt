package com.bankaccenture.retrofit.webclient

import com.bankaccenture.model.Transacao
import com.google.gson.annotations.SerializedName

data class ResponseTransacao(

    @field:SerializedName("statementList")
    val transacoesLista: List<Transacao>? = null,

    @field:SerializedName("error")
    val error: Error? = null
)