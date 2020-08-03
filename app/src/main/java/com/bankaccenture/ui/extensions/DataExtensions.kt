package com.bankaccenture.ui.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formataDataParaBrasileiro(): String {
    val formatoAmericano = "yyyy/MM/dd"
    val simpleDateFormat = SimpleDateFormat(formatoAmericano, Locale("pt", "br"))
    return simpleDateFormat.format(this.time)
}