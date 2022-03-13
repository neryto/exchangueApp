package com.nery.bustos.exchangueapp.domain

import com.nery.bustos.basemodule.DataState
import com.nery.bustos.exchangueapp.data.CurrencyData
import kotlinx.coroutines.flow.Flow

interface ListUseCase {
    suspend fun fetchList(): Flow<DataState<List<CurrencyData>>>
}