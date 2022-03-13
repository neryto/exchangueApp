package com.nery.bustos.exchangueapp.domain

import com.nery.bustos.basemodule.DataState
import com.nery.bustos.exchangueapp.App
import com.nery.bustos.exchangueapp.data.CurrencyData
import com.nery.bustos.exchangueapp.data.DataSource
import com.nery.bustos.exchangueapp.di.ProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ConverterUseCaseImpl : ConverterUseCase {

    private val dataSource: DataSource

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                ProviderEntryPoint.ProviderDataSource::class.java
            )
        dataSource = hiltEntryPoint.dataSource()
    }

    override suspend fun buildView(idBase: Int, idCurrency: Int)
            : Flow<DataState<Pair<CurrencyData, CurrencyData>>> = flow {
        val base = dataSource.getCurrencyFromId(idBase)
        val currency = dataSource.getCurrencyFromId(idCurrency)
        emit(DataState.Success(Pair(base, currency)))
    }

    override suspend fun calculate(
        baseCurrencyData: CurrencyData,
        currencyData: CurrencyData,
        amount: Double
    ): Flow<Double> = flow {
        when {
            baseCurrencyData.isBase -> {
                emit((baseCurrencyData.purchase * amount) * currencyData.purchase)
            }
            currencyData.isBase -> {
                emit((currencyData.purchase * amount) / baseCurrencyData.purchase)
            }
            else -> {
                emit((currencyData.purchase / baseCurrencyData.purchase)*amount)
            }
        }
    }

    override suspend fun swap(
        baseCurrencyData: CurrencyData,
        currencyData: CurrencyData
    ): Flow<DataState<Pair<CurrencyData, CurrencyData>>> = flow {
        emit(DataState.Success(Pair(currencyData, baseCurrencyData)))
    }
}