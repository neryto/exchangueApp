package com.nery.bustos.exchangueapp

import android.os.Bundle
import android.util.Log
import com.nery.bustos.basemodule.presentation.BaseActivity
import com.nery.bustos.exchangueapp.data.Id
import com.nery.bustos.exchangueapp.presentation.ui.FragmentConverter
import com.nery.bustos.exchangueapp.presentation.ui.FragmentList
import com.nery.bustos.exchangueapp.presentation.ui.FragmentSplash

class MainActivity : BaseActivity(), FragmentSplash.FragmentSplashHandler,
    FragmentConverter.FragmentConverterHandler, FragmentList.FragmentListHandler {

    private val containerId = R.id.mainContainer
    private var idBase = Id.USA.ordinal
    private var idCurrency = Id.MEX.ordinal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(containerId, FragmentSplash.newInstance(this), FragmentSplash.TAG)
    }

    override fun goToConverter() {
        removeCurrentFragment(containerId)
        addFragment(
            containerId, FragmentConverter
                .newInstance(
                    idBase,
                    idCurrency,
                    this
                ), FragmentConverter.TAG
        )
    }

    override fun changeCurrency(baseCurrency: Int) {
        idBase = baseCurrency
        removeCurrentFragment(containerId)
        addFragment(
            containerId, FragmentList
                .newInstance(ChangeType.CURRENCY, this), FragmentList.TAG
        )
    }

    override fun changeBase(currency: Int) {
        idCurrency = currency
        removeCurrentFragment(containerId)
        addFragment(
            containerId, FragmentList
                .newInstance(ChangeType.BASE, this), FragmentList.TAG
        )
    }

    override fun onCurrencySelected(id: Int) {
        removeCurrentFragment(containerId)
        addFragment(
            containerId,
            FragmentConverter.newInstance(idBase, id, this),
            FragmentConverter.TAG
        )
    }

    override fun onBaseSelected(id: Int) {
        removeCurrentFragment(containerId)
        addFragment(
            containerId,
            FragmentConverter.newInstance(id, idCurrency, this),
            FragmentConverter.TAG
        )
    }

}

enum class ChangeType {
    BASE, CURRENCY
}