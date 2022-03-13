package com.nery.bustos.exchangueapp.presentation.view

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.nery.bustos.basemodule.DataState
import com.nery.bustos.basemodule.presentation.BaseView
import com.nery.bustos.exchangueapp.data.CurrencyData
import com.nery.bustos.exchangueapp.databinding.FragmentConverterBinding
import com.nery.bustos.exchangueapp.presentation.ConverterViewModel

class ConverterView
constructor(private val idBase: Int, private val idCurrency: Int) :
    BaseView<FragmentConverterBinding, ConverterActions>() {

    private val viewModel: ConverterViewModel =
        ViewModelProvider(this)[ConverterViewModel::class.java]
    lateinit var baseCurrencyData: CurrencyData
    lateinit var currencyData: CurrencyData

    fun converterViewInit(
        lifecycleOwner: LifecycleOwner,
        actionHandler: (action: ConverterActions, value: Any?) -> Unit
    ) {
        super.init(lifecycleOwner, actionHandler)
    }

    override fun initObservers() {
        lifecycleOwner.lifecycle.addObserver(this)
        viewModel.fetchInfo.observe(lifecycleOwner) {
            if (it is DataState.Success) {
                baseCurrencyData = it.data.first
                currencyData = it.data.second
                updateView()

            }
        }
        viewModel.calculateData.observe(lifecycleOwner) {
            mBinding.edtReceiver.setText(it.toString())
        }
        mBinding.apply {
            btnStart.setOnClickListener {
                viewModel.calculate(
                    baseCurrencyData,
                    currencyData,
                    if (mBinding.edtSend.text.isEmpty()) 0.0
                    else
                        mBinding.edtSend.text.toString().toDouble()
                )
            }
            btnDollars.setOnLongClickListener {
                actionHandler(ConverterActions.CHANGE_BASE, currencyData.id)

                true
            }
            btnCurrency.setOnLongClickListener {
                actionHandler(ConverterActions.CHANGE_CURRENCY, baseCurrencyData.id)
                true
            }
            btnChange.setOnClickListener {
                viewModel.swap(baseCurrencyData, currencyData)
            }
        }

    }

    private fun updateView() {
        mBinding.apply {
            edtSend.setText("")
            edtReceiver.setText("")
            btnDollars.text = baseCurrencyData.currencyName
            btnCurrency.text = currencyData.currencyName
            tvInfo.text = "Compra ${currencyData.purchase} | Venta ${currencyData.sale}"
        }
    }

    override fun setupViewBinding(view: View): FragmentConverterBinding {
        binding = FragmentConverterBinding.bind(view)
        return binding as FragmentConverterBinding
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.buildView(idBase, idCurrency)
    }
}