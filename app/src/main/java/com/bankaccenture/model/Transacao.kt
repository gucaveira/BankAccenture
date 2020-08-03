package com.bankaccenture.model

import java.io.Serializable
import java.math.BigDecimal

data class Transacao(
    val title: String? = null,
    val desc: String? = null,
    val date: String? = null,
    val value: BigDecimal? = null
) : Serializable
