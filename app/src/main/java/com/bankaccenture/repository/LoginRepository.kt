package com.bankaccenture.repository

import androidx.lifecycle.LiveData
import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.retrofit.webclient.WebClient

class LoginRepository(private val webClient: WebClient) {

    fun login(loginUsuario: LoginUsuario): LiveData<ContaUsuario> {
        return webClient.login(loginUsuario)
    }
}