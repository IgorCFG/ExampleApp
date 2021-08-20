package com.igdev.exampleapp.ui.checkin

import androidx.lifecycle.ViewModel
import com.igdev.exampleapp.managers.interfaces.IViewManager
import com.igdev.exampleapp.models.Checkin
import com.igdev.exampleapp.repositories.interfaces.IEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CheckinDialogViewModel @Inject constructor(
    private val eventRepository: IEventRepository
): ViewModel() {
    //region Fields

    var eventId: String = ""
    var name: String = ""
    var email: String = ""

    //endregion

    //region Public Methods

    fun sendCheckin(onSend: () -> Unit) = runBlocking {
        launch(Dispatchers.IO) {
            val checkin = Checkin(eventId, name, email)
            eventRepository.sendCheckIn(checkin)

            onSend()
        }
    }

    //endregion
}