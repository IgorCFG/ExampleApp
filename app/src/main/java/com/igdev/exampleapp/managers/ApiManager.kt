package com.igdev.exampleapp.managers

import com.igdev.exampleapp.Constants
import com.igdev.exampleapp.api.RetrofitApi
import com.igdev.exampleapp.managers.interfaces.IApiManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiManager @Inject constructor(): IApiManager {
    @Volatile
    private var instanceCache: RetrofitApi? = null

    override fun getInstance(): RetrofitApi? {
        synchronized(this) {
            var instance = instanceCache
            if (instance != null) return instance

            val httpClient = OkHttpClient.Builder()
                .connectTimeout(Constants.Api.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.Api.READ_TIMEOUT, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

            instance = retrofit.create(RetrofitApi::class.java)
            instanceCache = instance

            return instance
        }
    }
}