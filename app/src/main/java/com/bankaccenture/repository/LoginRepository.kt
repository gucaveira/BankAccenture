package com.bankaccenture.repository

import androidx.lifecycle.LiveData
import com.bankaccenture.model.UserAccount
import com.bankaccenture.model.LoginUser
import com.bankaccenture.retrofit.webclient.WebClient

class LoginRepository(private val webClient: WebClient) {

    fun login(loginUser: LoginUser): LiveData<UserAccount> {
        return webClient.login(loginUser)
    }
}