package com.igdev.exampleapp.repositories

import com.igdev.exampleapp.managers.interfaces.IApiManager
import com.igdev.exampleapp.managers.interfaces.IViewManager
import com.igdev.exampleapp.models.Checkin
import com.igdev.exampleapp.models.Code
import com.igdev.exampleapp.models.Event
import com.igdev.exampleapp.repositories.interfaces.IEventRepository
import javax.inject.Inject

class EventRepository @Inject constructor(
    apiManager: IApiManager,
    private val viewManager: IViewManager
): IEventRepository {
    //region Properties

    private val apiInstance = apiManager.getInstance()!!

    //endregion

    //region Override Methods

    override suspend fun getEvents(): List<Event> {
        viewManager.startLoading("Buscando eventos...")
        val events = apiInstance.getEvents()

        viewManager.stopLoading()
        return events
    }

    override suspend fun getEventById(id: String): Event {
        viewManager.startLoading("Buscando detalhes do evento...")
        val event = apiInstance.getEventById(id)

        viewManager.stopLoading()
        return event
    }

    override suspend fun sendCheckIn(checkin: Checkin): Code {
        viewManager.startLoading("Enviando check-in...")
        val isSent = apiInstance.postCheckIn(checkin)

        viewManager.stopLoading()
        return isSent
    }

    //endregion
}