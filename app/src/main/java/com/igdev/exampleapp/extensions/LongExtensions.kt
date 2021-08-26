package com.igdev.exampleapp.extensions

import java.util.*

fun Long.toCalendar(): Calendar {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = this

    return calendar
}