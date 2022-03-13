package com.nery.bustos.exchangueapp.presentation.view

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nery.bustos.basemodule.DataState
import com.nery.bustos.basemodule.presentation.BaseView
import com.nery.bustos.exchangueapp.ChangeType
import com.nery.bustos.exchangueapp.databinding.FragmentListBinding
import com.nery.bustos.exchangueapp.presentation.ListViewModel
import com.nery.bustos.exchangueapp.presentation.ui.ListAdapter

class ListView constructor(private val changeType: ChangeType) :
    BaseView<FragmentListBinding, ListActions>() {

    private val viewModel: ListViewModel =
        ViewModelProvider(this)[ListViewModel::class.java]

    fun listViewInit(
        lifecycleOwner: LifecycleOwner,
        actionHandler: (action: ListActions, value: Any?) -> Unit
    ) {
        super.init(lifecycleOwner, actionHandler)
    }

    override fun initObservers() {
        lifecycleOwner.lifecycle.addObserver(this)
        viewModel.fetchInfo.observe(lifecycleOwner) {
            if (it is DataState.Success) {
                mBinding.list.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ListAdapter(it.data, onClick)
                }
            }
        }
    }

    private val onClick: (id: Int) -> Unit = {
        when(changeType){
            ChangeType.BASE -> actionHandler(ListActions.ON_BASE_SELECTED, it)
            ChangeType.CURRENCY -> actionHandler(ListActions.ON_CURRENCY_SELECTED, it)
        }

    }

    override fun setupViewBinding(view: View): FragmentListBinding {
        binding = FragmentListBinding.bind(view)
        return binding as FragmentListBinding
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.fetchList()
    }
}