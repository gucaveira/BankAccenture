package com.bankaccenture.retrofit.service

import com.bankaccenture.model.UserAccount
import com.bankaccenture.retrofit.webclient.ResponseLogin
import com.bankaccenture.retrofit.webclient.ResponseTransactions
import retrofit2.Call
import retrofit2.http.*

interface NetworkApiSource {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") login: String,
        @Field("password") password: String
    ): Call<ResponseLogin<UserAccount>>

    @GET("statements/{id}")
    fun getListTransactions(@Path("id") userId: Int): Call<ResponseTransactions>
}