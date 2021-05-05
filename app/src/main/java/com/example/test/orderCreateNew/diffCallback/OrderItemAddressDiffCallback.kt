package com.example.test.orderCreateNew.diffCallback

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.test.Model.OrderItemAddress

class OrderItemAddressDiffCallback : DiffUtil.ItemCallback<OrderItemAddress>() {
    override fun areItemsTheSame(oldItem: OrderItemAddress, newItem: OrderItemAddress): Boolean{
        var isEquals = oldItem.id == newItem.id
        Log.d("OrderItemAddress", "areItemTheSame $isEquals")
        return isEquals
    }
    override fun areContentsTheSame(oldItem: OrderItemAddress, newItem: OrderItemAddress): Boolean{
        val test = oldItem == newItem
        Log.d("OrderItemAddress", "areContentsTheSame $test")
        return test
    }

    override fun getChangePayload(oldItem: OrderItemAddress, newItem: OrderItemAddress): Any? {
        return Any()
    }
}