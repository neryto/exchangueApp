package com.nery.bustos.exchangueapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nery.bustos.basemodule.presentation.BaseViewModel
import com.nery.bustos.exchangueapp.App
import com.nery.bustos.exchangueapp.data.CurrencyData
import com.nery.bustos.exchangueapp.di.ProviderEntryPoint
import com.nery.bustos.exchangueapp.domain.ConverterUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ConverterViewModel : BaseViewModel<Pair<CurrencyData, CurrencyData>>() {


    private val _calculateData = MutableLiveData<Double>()
    val calculateData: LiveData<Double> get() = _calculateData
    private var useCase: ConverterUseCase

    init {
        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(
                App.applicationContext(),
                ProviderEntryPoint.ProviderConverterUseCase::class.java
            )
        useCase = hiltEntryPoint.converterUseCase()
    }

    fun buildView(idBase: Int, idCurrency: Int) {
        viewModelScope.launch {
            useCase.buildView(idBase, idCurrency).collect {
                _fetchInfo.postValue(it)
            }
        }
    }

    fun calculate(baseCurrencyData: CurrencyData, currencyData: CurrencyData, amount: Double) {
        viewModelScope.launch {
            useCase.calculate(baseCurrencyData, currencyData, amount).collect {
                _calculateData.postValue(it)
            }

        }
    }

    fun swap(baseCurrencyData: CurrencyData, currencyData: CurrencyData) {
        viewModelScope.launch {
            useCase.swap(baseCurrencyData, currencyData).collect {
                _fetchInfo.postValue(it)
            }
        }
    }
}