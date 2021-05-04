package com.example.test.orderCreateNew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderCreateViewModelFactory () : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {

            modelClass.isAssignableFrom(OrderCreateNewViewModel::class.java) ->
                OrderCreateNewViewModel() as T



            else -> throw  IllegalArgumentException("Unknown ViewModel class")
        }
    }
}