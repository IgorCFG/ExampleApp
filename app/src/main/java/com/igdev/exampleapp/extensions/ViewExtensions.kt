package com.igdev.exampleapp.extensions

import android.view.View

fun View?.show() =
    this?.apply { visibility = View.VISIBLE }

fun View?.hide() =
    this?.apply { visibility = View.GONE }