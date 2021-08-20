package com.igdev.exampleapp.extensions

import java.text.DateFormatSymbols

fun Int.toShortMonth(): String {
    return DateFormatSymbols().shortMonths[this]
}