package com.igdev.exampleapp.extensions

import android.widget.TextView

fun TextView.isNullOrEmpty(): Boolean =
    this.text.isNullOrEmpty()