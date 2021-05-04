package com.example.test.orderCreateNew

import com.example.test.Model.OrderItemAddress

data class OrderCreateNewModel(
    var addresses: List<OrderItemAddress> = listOf(OrderItemAddress.make())
)