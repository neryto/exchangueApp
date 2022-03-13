package com.nery.bustos.exchangueapp.di

import com.nery.bustos.exchangueapp.data.DataSource
import com.nery.bustos.exchangueapp.domain.ConverterUseCase
import com.nery.bustos.exchangueapp.domain.ListUseCase
import com.nery.bustos.exchangueapp.domain.SplashUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class ProviderEntryPoint {
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderSplashUseCase {
        fun splashUseCase(): SplashUseCase
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderConverterUseCase {
        fun converterUseCase(): ConverterUseCase
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderListUseCase {
        fun listUseCase(): ListUseCase
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ProviderDataSource {
        fun dataSource(): DataSource
    }
}