package com.nery.bustos.exchangueapp.presentation.view

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.nery.bustos.basemodule.presentation.BaseView
import com.nery.bustos.exchangueapp.databinding.FragmentSplashBinding
import com.nery.bustos.exchangueapp.presentation.SplashViewModel

class SplashView : BaseView<FragmentSplashBinding, SplashActions>() {

    private val viewModel: SplashViewModel =
        ViewModelProvider(this)[SplashViewModel::class.java]

    fun initSplashView(
        lifecycleOwner: LifecycleOwner,
        actionHandler: (action: SplashActions, value: Any?) -> Unit
    ) {
        super.init(lifecycleOwner, actionHandler)
    }

    override fun initObservers() {
        lifecycleOwner.lifecycle.addObserver(this)
        viewModel.fetchInfo.observe(lifecycleOwner){
           mBinding.lottieView.cancelAnimation()
           actionHandler(SplashActions.GO_TO_CONVERTER,null)
        }
    }

    override fun setupViewBinding(view: View): FragmentSplashBinding {
        binding = FragmentSplashBinding.bind(view)
        return binding as FragmentSplashBinding
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mBinding.lottieView.playAnimation()
        viewModel.fetchDataDummy()
    }
}