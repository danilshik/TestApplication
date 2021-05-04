package com.example.test.orderCreateNew.diffCallback

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.test.Model.OrderItemAddress
import com.example.test.Model.OrderItemProduct

class OrderItemProductDiffCallback : DiffUtil.ItemCallback<OrderItemProduct>() {
    override fun areItemsTheSame(oldItem: OrderItemProduct, newItem: OrderItemProduct) :Boolean {
        val test = oldItem.id == newItem.id
        Log.d("OrderItemProduct", "areItemTheSame $test")
        return test
    }

    override fun areContentsTheSame(oldItem: OrderItemProduct, newItem: OrderItemProduct): Boolean {
        val test = oldItem == newItem
        Log.d("OrderItemProduct", "areContentsTheSame $test")
        return test
    }
}