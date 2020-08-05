package com.bankaccenture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bankaccenture.model.Transaction
import com.bankaccenture.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    fun allTransaction(userId: Int): LiveData<List<Transaction>?> {
        return repository.allTransaction(userId)
    }
}