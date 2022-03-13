package com.nery.bustos.exchangueapp.domain

import com.nery.bustos.basemodule.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SplashUseCaseImpl : SplashUseCase {
    override suspend fun fetchDataDummy(): Flow<DataState<Any>> = flow {
        kotlinx.coroutines.delay(4000)
        emit(DataState.Success(Any()))
    }
}