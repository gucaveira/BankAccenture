package com.bankaccenture.retrofit.webclient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bankaccenture.model.APIError
import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.model.LoginUsuario
import com.bankaccenture.retrofit.AppRetrofit
import com.bankaccenture.retrofit.service.NetworkApiSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebClient(private val service: NetworkApiSource = AppRetrofit().networkApiSource) {

    private fun <T> execultRequisicao(call: Call<T>) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                t.message?.let { Log.e("ERROR API", it) }
                Resource(dado = null, error = APIError(mensagem = t.message))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    Resource(dado = response.body(), error = null)

                }
            }
        })
    }

    fun login(loginUsuario: LoginUsuario): LiveData<Resource<ContaUsuario>> {
        //execultRequisicao(service.login(loginUsuario.emailCpf, loginUsuario.senha))

        return MutableLiveData<Resource<ContaUsuario>>()
    }
}