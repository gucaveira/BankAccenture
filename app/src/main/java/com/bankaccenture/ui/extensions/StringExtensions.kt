package com.bankaccenture.ui.extensions

fun String.formatAgency(): String {
    var str = this
    if (str.length > 7) {
        str = StringBuilder(str).insert(str.length - 1, "-")
            .insert(str.length - 7, ".")
            .toString()
    }
    return str
}