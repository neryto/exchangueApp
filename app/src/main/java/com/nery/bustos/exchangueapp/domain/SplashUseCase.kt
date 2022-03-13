package com.nery.bustos.exchangueapp.domain

import com.nery.bustos.basemodule.DataState
import kotlinx.coroutines.flow.Flow

interface SplashUseCase {
   suspend fun fetchDataDummy(): Flow<DataState<Any>>
}