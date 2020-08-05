package com.bankaccenture.ui.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatForCoinBrazilian(): String {
    val formatCoinBrazilian = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatCoinBrazilian.format(this)
        .format(this).replace("-R$", "R$ -")
}