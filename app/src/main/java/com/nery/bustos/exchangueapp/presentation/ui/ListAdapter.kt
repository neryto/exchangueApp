package com.nery.bustos.exchangueapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nery.bustos.exchangueapp.App
import com.nery.bustos.exchangueapp.R
import com.nery.bustos.exchangueapp.data.CurrencyData
import com.nery.bustos.exchangueapp.databinding.ItemListBinding

class ListAdapter
constructor(private val list: List<CurrencyData>, private val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {


    class ListViewHolder constructor(private val mBinding: ItemListBinding) :
        RecyclerView.ViewHolder(mBinding.root.rootView) {

        fun bind(item: CurrencyData, onClick: (id: Int) -> Unit) {
            mBinding.apply {
                imgViewFlag.setImageDrawable(App.applicationContext().getDrawable(item.flag))
                tvName.text = item.name
                tvInfo.text = "1 USD = ${item.purchase} ${item.acronym}"
                mBinding.root.setOnClickListener {
                    onClick(item.id)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        val mBinding = ItemListBinding.bind(mView)
        return ListViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size
}