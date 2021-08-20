package com.igdev.exampleapp.managers.interfaces

import com.igdev.exampleapp.api.RetrofitApi

interface IApiManager {
    fun getInstance(): RetrofitApi?
}