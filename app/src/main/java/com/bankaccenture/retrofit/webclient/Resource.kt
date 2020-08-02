package com.bankaccenture.retrofit.webclient

import com.bankaccenture.model.ApiError
import com.bankaccenture.model.ContaUsuario

class Resource(val userAccount: ContaUsuario? = null, val error: ApiError? = null)
