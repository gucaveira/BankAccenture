package com.bankaccenture.retrofit.webclient

import com.bankaccenture.model.APIError

class Resource<T>( val dado: T, private val error: APIError?)
