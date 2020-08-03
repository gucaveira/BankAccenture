package com.bankaccenture.model

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

data class Transacao(
    val title: String,
    val desc: String,
    val date: Date,
    val value: BigDecimal
) : Serializable
