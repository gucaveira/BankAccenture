package com.bankaccenture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.repository.LoginRepository
import com.bankaccenture.utils.AppUtils

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun login(loginUsuario: LoginUsuario): LiveData<ContaUsuario> {
        return repository.login(loginUsuario)
    }

    fun isSenhaValida(password: String): Boolean {
        return AppUtils.validatePassword(password)
    }
}