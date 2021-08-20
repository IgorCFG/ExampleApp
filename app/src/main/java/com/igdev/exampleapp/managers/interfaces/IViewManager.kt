package com.igdev.exampleapp.managers.interfaces

import androidx.lifecycle.MutableLiveData

interface IViewManager {
    fun getLoading(): MutableLiveData<String>
    fun startLoading(loading: String)
    fun stopLoading()

    fun showSnackbar(message: String)
    fun getSnackbar(): MutableLiveData<String>
}