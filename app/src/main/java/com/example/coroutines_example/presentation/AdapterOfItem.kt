package com.example.coroutines_example.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.coroutines_example.R
import com.example.coroutines_example.data.api.MyDataItem
import com.example.coroutines_example.databinding.ItemBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class AdapterOfItem(val employeeTexttItem: MyDataItem) ://Adapter
    AbstractBindingItem<ItemBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: ItemBinding, payloads: List<Any>) {
        binding.txtTopic.text = employeeTexttItem.name
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemBinding {
        return ItemBinding.inflate(inflater, parent, false)
    }
}