package com.bankaccenture.model

import java.io.Serializable

data class ContaUsuario(
    val userId: Int? = null,
    val name: String? = null,
    val bankAccount: String? = null,
    val agency: String? = null,
    val balance: Double? = null
) : Serializable