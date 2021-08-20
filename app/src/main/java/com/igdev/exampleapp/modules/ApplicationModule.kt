package com.igdev.exampleapp.modules

import com.igdev.exampleapp.managers.ApiManager
import com.igdev.exampleapp.managers.ViewManager
import com.igdev.exampleapp.managers.interfaces.IApiManager
import com.igdev.exampleapp.managers.interfaces.IViewManager
import com.igdev.exampleapp.repositories.EventRepository
import com.igdev.exampleapp.repositories.interfaces.IEventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {
    @Binds
    @Singleton
    abstract fun bindApiManager(apiManager: ApiManager): IApiManager

    @Binds
    @Singleton
    abstract fun bindViewManager(viewManager: ViewManager): IViewManager

    @Binds
    abstract fun bindEventRepository(eventRepository: EventRepository): IEventRepository
}