package com.nery.bustos.exchangueapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nery.bustos.exchangueapp.App
import java.io.IOException

class DataSourceImpl : DataSource {

    override suspend fun getCurrencyFromId(id: Int): CurrencyData {
        val ls = getAllCurrency().filter {
            it.id == id
        }
        return if (!ls.isNullOrEmpty())
            ls.first()
        else CurrencyData(
            0, "", "", "", 0, false, 0.0, 0.0
        )
    }

   override suspend fun getAllCurrency(): List<CurrencyData> {
        getJsonDataFromAsset(App.applicationContext(), "data.json")?.let {
            val tokenType = object : TypeToken<List<CurrencyData>>() {}.type
            return Gson().fromJson(it, tokenType)
        } ?: kotlin.run {
            return listOf()
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}