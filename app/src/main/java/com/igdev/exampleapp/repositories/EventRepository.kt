package com.igdev.exampleapp.repositories

import com.igdev.exampleapp.managers.interfaces.IApiManager
import com.igdev.exampleapp.managers.interfaces.IViewManager
import com.igdev.exampleapp.models.Checkin
import com.igdev.exampleapp.models.Code
import com.igdev.exampleapp.models.Event
import com.igdev.exampleapp.repositories.interfaces.IEventRepository
import java.lang.Exception
import javax.inject.Inject

class EventRepository @Inject constructor(
    apiManager: IApiManager,
    private val viewManager: IViewManager
): IEventRepository {
    //region Properties

    private val apiInstance = apiManager.getInstance()

    //endregion

    //region Override Methods

    override suspend fun getEvents(): List<Event>? {
        viewManager.startLoading("Buscando eventos...")
        var events: List<Event>? = null
        try {
            events = apiInstance.getEvents()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            viewManager.stopLoading()
        }

        return events
    }

    override suspend fun getEventById(id: String): Event? {
        viewManager.startLoading("Buscando detalhes do evento...")
        var event: Event? = null
        try {
            event = apiInstance.getEventById(id)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            viewManager.stopLoading()
        }

        return event
    }

    override suspend fun sendCheckIn(checkin: Checkin): Boolean {
        viewManager.startLoading("Enviando check-in...")
        var code: Code? = null
        try {
            code = apiInstance.postCheckIn(checkin)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            viewManager.stopLoading()
        }

        viewManager.stopLoading()

        val isSent = code?.code == 200
        if (isSent)
            viewManager.showSnackbar("Check-in enviado com sucesso!")
        else
            viewManager.showSnackbar("Ocorreu o erro ao tentar enviar o check-in, tente novamente.")

        return isSent
    }

    //endregion
}