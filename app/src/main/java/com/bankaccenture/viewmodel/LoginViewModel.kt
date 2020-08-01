package com.bankaccenture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.repository.LoginRepository
import com.bankaccenture.retrofit.webclient.Resource

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun login(loginUsuario: LoginUsuario): LiveData<Resource<ContaUsuario>> {
        return repository.login(loginUsuario)
    }
}