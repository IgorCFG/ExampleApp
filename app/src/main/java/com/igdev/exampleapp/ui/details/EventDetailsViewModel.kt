package com.igdev.exampleapp.ui.details

import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.igdev.exampleapp.extensions.toCalendar
import com.igdev.exampleapp.extensions.toPriceFormat
import com.igdev.exampleapp.extensions.toShortMonth
import com.igdev.exampleapp.models.Event
import com.igdev.exampleapp.repositories.interfaces.IEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    application: Application,
    private val eventRepository: IEventRepository
): AndroidViewModel(application) {
    //region Properties

    private var event = MutableLiveData<Event>()
    private var eventDate = MutableLiveData<String>()
    private var entryPrice = MutableLiveData<String>()
    private var eventAddress = MutableLiveData("Buscando endereço...")

    //endregion

    //region Getters

    fun getEvent(): MutableLiveData<Event> = event
    fun getEventDate(): MutableLiveData<String> = eventDate
    fun getEntryPrice(): MutableLiveData<String> = entryPrice
    fun getEventAddress(): MutableLiveData<String> = eventAddress

    //endregion

    //region Public Methods

    fun getEventDetails(eventId: String) = runBlocking {
        launch(Dispatchers.IO) {
             eventRepository.getEventById(eventId)?.let {
                 makeEventDateText(it)
                 makeEntryPriceText(it)
                 GlobalScope.async {
                     makeEventAddressText(it)
                 }
                 event.postValue(it)
             }
        }
    }

    //endregion

    //region Private Methods

    private fun makeEventDateText(event: Event) {
        val calendar = event.date.toCalendar()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH).toShortMonth()
        val year = calendar.get(Calendar.YEAR)
        val time = calendar.get(Calendar.HOUR_OF_DAY)

        val text = "Evento acontecerá dia $day de $month de $year às ${time}h"
        eventDate.postValue(text)
    }

    private fun makeEntryPriceText(event: Event) {
        val text = "Valor da entrada ${event.price.toPriceFormat()}"
        entryPrice.postValue(text)
    }

    private fun makeEventAddressText(event: Event) {
        var address = "endereço não encontrado"
        try {
            val geocoder = Geocoder(getApplication())
            val addresses = geocoder.getFromLocation(event.latitude, event.longitude, 1)

            address = addresses[0].getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        eventAddress.postValue(address)
    }

    //endregion
}