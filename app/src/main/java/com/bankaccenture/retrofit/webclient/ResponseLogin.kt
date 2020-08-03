package com.bankaccenture.retrofit.webclient

import com.bankaccenture.model.ApiError

class ResponseLogin<T>(val userAccount: T? = null, val error: ApiError? = null)
