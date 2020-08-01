package com.bankaccenture.model

import java.io.Serializable

data class ContaUsuario(
    var userId: Int? = null,
    var name: String? = null,
    var bankAccount: String? = null,
    var agency: String? = null,
    var balance: Double? = null
) : Serializable
