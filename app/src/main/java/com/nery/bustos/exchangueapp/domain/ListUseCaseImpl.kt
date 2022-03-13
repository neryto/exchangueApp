package com.nery.bustos.exchangueapp.domain

import com.nery.bustos.basemodule.DataState
import com.nery.bustos.exchangueapp.App
import com.nery.bustos.exchangueapp.data.CurrencyData
import com.nery.bustos.exchangueapp.data.DataSource
import com.nery.bustos.exchangueapp.di.ProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListUseCaseImpl : ListUseCase {
    private val dataSource: DataSource

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                ProviderEntryPoint.ProviderDataSource::class.java
            )
        dataSource = hiltEntryPoint.dataSource()
    }

    override suspend fun fetchList(): Flow<DataState<List<CurrencyData>>> = flow {
        emit(DataState.Success(dataSource.getAllCurrency()))
    }
}