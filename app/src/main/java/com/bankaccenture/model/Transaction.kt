package com.bankaccenture.model

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

data class Transaction(
    val title: String? = null,
    val desc: String? = null,
    val date: Date? = null,
    val value: BigDecimal? = null
) : Serializable
