package com.bankaccenture.retrofit.webclient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bankaccenture.model.LoginUser
import com.bankaccenture.model.Transaction
import com.bankaccenture.model.UserAccount
import com.bankaccenture.retrofit.AppRetrofit
import com.bankaccenture.retrofit.service.NetworkApiSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebClient(private val service: NetworkApiSource = AppRetrofit().networkApiSource) {

    private fun <T> exeRequest(
        call: Call<T>, whenSuccessful: (dado: T?) -> Unit, whenFall: (error: String?) -> Unit) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                throwable.message?.let { Log.e("ERROR API", it) }
                whenFall(throwable.message)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    whenSuccessful(response.body())
                }
            }
        })
    }

    fun login(loginUser: LoginUser): LiveData<UserAccount> {
        val mutableLiveData = MutableLiveData<UserAccount>()
        exeRequest(service.login(loginUser.emailCpf, loginUser.password),
            whenSuccessful = { userAccount ->
                userAccount?.let {
                    mutableLiveData.value = userAccount.userAccount
                }
            }, whenFall = {

            })
        return mutableLiveData
    }

    fun allTransactions(userId: Int): LiveData<List<Transaction>?> {
        val mutableLiveData = MutableLiveData<List<Transaction>?>()
        exeRequest(service.getListTransactions(userId), whenSuccessful = { responseTransactions ->
            responseTransactions?.let {
                mutableLiveData.value = it.transactionsList
            }
        },
            whenFall = {
            })
        return mutableLiveData
    }
}