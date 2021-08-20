package com.igdev.exampleapp.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.igdev.exampleapp.repositories.interfaces.IEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventRepository: IEventRepository
): ViewModel() {
    //region Properties

    var events = liveData(Dispatchers.IO) {
        val events = eventRepository.getEvents()
        emit(events)
    }

    private var eventSelected = MutableLiveData<String>()

    //endregion

    //region Getters

    fun getEventSelected(): MutableLiveData<String> = eventSelected

    //endregion

    //region Public Methods

    fun onEventSelected(eventId: String) {
        eventSelected.postValue(eventId)
    }

    //endregion
}