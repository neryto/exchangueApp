package com.nery.bustos.exchangueapp.data

interface DataSource {
   suspend fun getCurrencyFromId(id: Int): CurrencyData
   suspend fun getAllCurrency() : List<CurrencyData>
}