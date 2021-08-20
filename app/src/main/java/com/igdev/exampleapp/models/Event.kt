package com.igdev.exampleapp.models

data class Event(
    val id: String,
    val people: List<Person>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Float,
    val title: String
)
