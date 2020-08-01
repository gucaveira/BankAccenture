package com.bankaccenture.model

import java.io.Serializable

data class APIError(
    private var code: Int? = null,
    private var mensagem: String? = null
) : Serializable