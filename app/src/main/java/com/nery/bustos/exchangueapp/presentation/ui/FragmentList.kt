package com.nery.bustos.exchangueapp.presentation.ui

import android.view.View
import com.nery.bustos.basemodule.presentation.BaseFragment
import com.nery.bustos.exchangueapp.ChangeType
import com.nery.bustos.exchangueapp.R
import com.nery.bustos.exchangueapp.presentation.view.ListActions
import com.nery.bustos.exchangueapp.presentation.view.ListView

class FragmentList
private constructor() : BaseFragment() {

    interface FragmentListHandler {
        fun onCurrencySelected(id: Int)
        fun onBaseSelected(id: Int)
    }

    companion object {
        val TAG = FragmentList::class.java.name
        fun newInstance(
            changeType: ChangeType,
            handler: FragmentListHandler
        ): FragmentList = FragmentList().apply {
            this.handler = handler
            this.changeType = changeType
        }
    }

    lateinit var handler: FragmentListHandler
    lateinit var mView: ListView
    lateinit var changeType: ChangeType

    override fun setLayout(): Int = R.layout.fragment_list

    override fun setupView(view: View) {
        mView = ListView(changeType).apply {
            setupViewBinding(view)
            listViewInit(this@FragmentList, actionsHandler)
        }
    }

    private val actionsHandler: (action: ListActions, value: Any?) -> Unit =
        { action: ListActions, value: Any? ->
            when (action) {
                ListActions.ON_CURRENCY_SELECTED -> handler.onCurrencySelected(value as Int)
                ListActions.ON_BASE_SELECTED -> handler.onBaseSelected(value as Int)
            }
        }
}