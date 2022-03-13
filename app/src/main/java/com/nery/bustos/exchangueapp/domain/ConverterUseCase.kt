package com.nery.bustos.exchangueapp.domain

import com.nery.bustos.basemodule.DataState
import com.nery.bustos.exchangueapp.data.CurrencyData
import kotlinx.coroutines.flow.Flow

interface ConverterUseCase {
    suspend fun buildView(idBase: Int, idCurrency: Int)
            : Flow<DataState<Pair<CurrencyData, CurrencyData>>>

    suspend fun calculate(
        baseCurrencyData: CurrencyData,
        currencyData: CurrencyData,
        amount: Double
    )
            : Flow<Double>

    suspend fun swap(baseCurrencyData: CurrencyData, currencyData: CurrencyData):
            Flow<DataState<Pair<CurrencyData, CurrencyData>>>

}