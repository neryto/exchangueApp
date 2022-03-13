package com.nery.bustos.exchangueapp.presentation.ui

import android.os.Bundle
import android.view.View
import com.nery.bustos.basemodule.presentation.BaseFragment
import com.nery.bustos.exchangueapp.R
import com.nery.bustos.exchangueapp.data.Id
import com.nery.bustos.exchangueapp.presentation.view.ConverterActions
import com.nery.bustos.exchangueapp.presentation.view.ConverterView

const val ID_BASE = "ID_BASE"
const val ID_CURRENCY = "ID_CURRENCY"

class FragmentConverter
private constructor() : BaseFragment() {

    interface FragmentConverterHandler {
        fun changeCurrency(baseCurrency: Int)
        fun changeBase(currency: Int)
    }

    companion object {
        val TAG = FragmentConverter::class.java.name

        fun newInstance(
            idBase: Int, idCurrency:Int,
            handler: FragmentConverterHandler
        )
                : FragmentConverter = FragmentConverter().apply {
            arguments = Bundle().apply {
                putInt(ID_BASE,idBase)
                putInt(ID_CURRENCY,idCurrency)
            }
            this.handler = handler
        }
    }

    lateinit var mView: ConverterView
    lateinit var handler: FragmentConverterHandler

    override fun setLayout(): Int = R.layout.fragment_converter

    override fun setupView(view: View) {
        mView = ConverterView(
            arguments?.getInt(ID_BASE) ?: Id.USA.ordinal,
            arguments?.getInt(ID_CURRENCY) ?: Id.MEX.ordinal,

        ).apply {
            setupViewBinding(view)
            converterViewInit(this@FragmentConverter, actionsHandler)
        }
    }

    private val actionsHandler: (action: ConverterActions, value: Any?) -> Unit =
        { action: ConverterActions, value: Any? ->
            when (action) {
                ConverterActions.CHANGE_CURRENCY -> handler.changeCurrency(value as Int)
                ConverterActions.CHANGE_BASE -> handler.changeBase(value as Int)
            }
        }
}