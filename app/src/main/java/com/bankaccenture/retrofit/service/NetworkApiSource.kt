package com.bankaccenture.retrofit.service

import com.bankaccenture.model.ContaUsuario
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkApiSource {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") login: String, @Field("password") pass: String): Call<ContaUsuario>
}