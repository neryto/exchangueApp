package com.nery.bustos.exchangueapp.presentation.ui

import android.view.View
import com.nery.bustos.basemodule.presentation.BaseFragment
import com.nery.bustos.exchangueapp.R
import com.nery.bustos.exchangueapp.presentation.view.SplashActions
import com.nery.bustos.exchangueapp.presentation.view.SplashView

class FragmentSplash
private constructor() : BaseFragment() {

    interface FragmentSplashHandler{
        fun goToConverter()
    }

    companion object {
        val TAG = FragmentSplash::class.java.name
        fun newInstance(handler: FragmentSplashHandler): FragmentSplash = FragmentSplash().apply {
            this.handler = handler
        }
    }

    lateinit var mView: SplashView
    lateinit var handler: FragmentSplashHandler

    override fun setLayout(): Int = R.layout.fragment_splash

    override fun setupView(view: View) {
        mView = SplashView().apply {
            setupViewBinding(view)
            initSplashView(this@FragmentSplash, actionsHandler)
        }
    }

    private val actionsHandler: (action: SplashActions, value: Any?) -> Unit =
        { action: SplashActions, _: Any? ->
            when(action){
                SplashActions.GO_TO_CONVERTER -> handler.goToConverter()
            }
        }
}