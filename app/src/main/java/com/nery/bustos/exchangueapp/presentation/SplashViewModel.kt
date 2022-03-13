package com.nery.bustos.exchangueapp.presentation

import androidx.lifecycle.viewModelScope
import com.nery.bustos.basemodule.presentation.BaseViewModel
import com.nery.bustos.exchangueapp.App
import com.nery.bustos.exchangueapp.di.ProviderEntryPoint
import com.nery.bustos.exchangueapp.domain.SplashUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel
    : BaseViewModel<Any>() {

    private var useCase: SplashUseCase

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                ProviderEntryPoint.ProviderSplashUseCase::class.java
            )
        useCase = hiltEntryPoint.splashUseCase()
    }

    fun fetchDataDummy() {
        viewModelScope.launch {
            useCase.fetchDataDummy().collect {
                _fetchInfo.postValue(it)
            }
        }

    }

}