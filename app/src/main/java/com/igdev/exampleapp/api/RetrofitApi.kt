package com.igdev.exampleapp.api

import com.igdev.exampleapp.models.Checkin
import com.igdev.exampleapp.models.Code
import com.igdev.exampleapp.models.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

sealed interface RetrofitApi {
    @GET("/api/events")
    suspend fun getEvents(): List<Event>

    @GET("/api/events/{id}")
    suspend fun getEventById(@Path("id") id: String): Event

    @POST("/api/checkin")
    suspend fun postCheckIn(@Body checkin: Checkin): Code
}