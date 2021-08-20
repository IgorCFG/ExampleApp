package com.igdev.exampleapp.repositories.interfaces

import com.igdev.exampleapp.models.Checkin
import com.igdev.exampleapp.models.Code
import com.igdev.exampleapp.models.Event

interface IEventRepository {
    suspend fun getEvents(): List<Event>
    suspend fun getEventById(id: String): Event
    suspend fun sendCheckIn(checkin: Checkin)
}