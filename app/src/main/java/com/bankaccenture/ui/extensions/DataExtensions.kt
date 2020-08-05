package com.bankaccenture.ui.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDataForBrazilian(): String {
    val formatAmerican = "yyyy/MM/dd"
    val simpleDateFormatBrazilian = SimpleDateFormat(formatAmerican, Locale("pt", "br"))
    return simpleDateFormatBrazilian.format(this.time)
}
