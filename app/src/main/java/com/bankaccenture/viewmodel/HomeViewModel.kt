package com.bankaccenture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bankaccenture.model.Transacao
import com.bankaccenture.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    fun todasTrasacoes(usuarioId: Int): LiveData<List<Transacao>?> {
        return repository.todasTrasacoes(usuarioId)
    }
}