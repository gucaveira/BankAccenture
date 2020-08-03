package com.bankaccenture.retrofit.service

import com.bankaccenture.model.ContaUsuario
import com.bankaccenture.retrofit.webclient.ResponseLogin
import com.bankaccenture.retrofit.webclient.ResponseTransacao
import retrofit2.Call
import retrofit2.http.*

interface NetworkApiSource {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") login: String,
        @Field("password") pass: String
    ): Call<ResponseLogin<ContaUsuario>>

    @GET("statements/{id}")
    fun getListaTrasacoes(@Path("id") usuarioId: Int): Call<ResponseTransacao>
}