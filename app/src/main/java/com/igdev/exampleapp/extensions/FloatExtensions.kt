package com.igdev.exampleapp.extensions

import java.text.NumberFormat
import java.util.*

fun Float.toPriceFormat(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    format.minimumFractionDigits = 2
    format.maximumFractionDigits = 2

    return format.format(this)
}