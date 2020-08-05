package com.bankaccenture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bankaccenture.model.UserAccount
import com.bankaccenture.model.LoginUser
import com.bankaccenture.repository.LoginRepository
import com.bankaccenture.utils.AppUtils

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun login(loginUser: LoginUser): LiveData<UserAccount> {
        return repository.login(loginUser)
    }

    fun isPasswordValid(password: String): Boolean {
        return AppUtils.validatePassword(password)
    }
}