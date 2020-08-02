package com.bankaccenture.retrofit.webclient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.retrofit.AppRetrofit
import com.bankaccenture.retrofit.service.NetworkApiSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebClient(private val service: NetworkApiSource = AppRetrofit().networkApiSource) {

    private fun <T> execultRequisicao(
        call: Call<T>,
        quandoSucesso: (usuario: T?) -> Unit,
        quandoFalha: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                throwable.message?.let { Log.e("ERROR API", it) }
                quandoFalha(throwable.message)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    quandoSucesso(response.body())
                }
            }
        })
    }

    fun login(loginUsuario: LoginUsuario): LiveData<ContaUsuario> {
        val mutableLiveData = MutableLiveData<ContaUsuario>()
        execultRequisicao(service.login(loginUsuario.emailCpf, loginUsuario.senha),
            quandoSucesso = { contaUsuario ->
                contaUsuario?.let {
                    mutableLiveData.value = contaUsuario.userAccount
                }
            }, quandoFalha = {

            })
        return mutableLiveData
    }
}