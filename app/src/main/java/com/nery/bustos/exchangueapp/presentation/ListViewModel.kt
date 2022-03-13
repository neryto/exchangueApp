package com.nery.bustos.exchangueapp.presentation

import androidx.lifecycle.viewModelScope
import com.nery.bustos.basemodule.presentation.BaseViewModel
import com.nery.bustos.exchangueapp.App
import com.nery.bustos.exchangueapp.data.CurrencyData
import com.nery.bustos.exchangueapp.di.ProviderEntryPoint
import com.nery.bustos.exchangueapp.domain.ListUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel : BaseViewModel<List<CurrencyData>>() {

    private var useCase: ListUseCase

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                ProviderEntryPoint.ProviderListUseCase::class.java
            )
        useCase = hiltEntryPoint.listUseCase()
    }


    fun fetchList() {
        viewModelScope.launch {
            useCase.fetchList().collect {
                _fetchInfo.postValue(it)
            }
        }
    }

}