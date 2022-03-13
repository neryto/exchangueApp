package com.nery.bustos.exchangueapp.data

data class CurrencyData(
   val id: Int,
   val name : String,
   val currencyName : String,
   val acronym : String,
   val flag : Int,
   val isBase : Boolean = false,
   val purchase  : Double,
   val sale : Double,
)
