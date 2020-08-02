package com.bankaccenture.model

import java.io.Serializable

data class ApiError(
    val code: Int? = null,
    val message: String? = null
) : Serializable