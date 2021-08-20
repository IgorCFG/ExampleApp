package com.igdev.exampleapp.managers

import androidx.lifecycle.MutableLiveData
import com.igdev.exampleapp.managers.interfaces.IViewManager
import javax.inject.Inject

class ViewManager @Inject constructor(): IViewManager {
    //region Fields

    private var _loading = MutableLiveData<String>()
    private var _snackbarText = MutableLiveData<String>()

    //endregion

    //region Getters

    override fun getLoading(): MutableLiveData<String> = _loading

    override fun getSnackbar(): MutableLiveData<String> = _snackbarText

    //endregion

    //region Public Methods

    override fun startLoading(loading: String) = _loading.postValue(loading)
    override fun stopLoading() = _loading.postValue("")

    override fun showSnackbar(message: String) = _snackbarText.postValue(message)

    //endregion
}