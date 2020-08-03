package com.bankaccenture.repository

import androidx.lifecycle.LiveData
import com.bankaccenture.model.Transacao
import com.bankaccenture.retrofit.webclient.WebClient

class HomeRepository(private val webClient: WebClient) {

    fun todasTrasacoes(usuarioId: Int): LiveData<List<Transacao>?> {
        return webClient.todasTrasacoes(usuarioId)
    }
}