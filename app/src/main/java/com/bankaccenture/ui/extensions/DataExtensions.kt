package com.bankaccenture.ui.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDataForBrazilian(): String {
    val formatBrazilian = "dd/MM/yyyy"
    val simpleDateFormatBrazilian = SimpleDateFormat(formatBrazilian, Locale("pt", "br"))
    return simpleDateFormatBrazilian.format(this.time)
}
