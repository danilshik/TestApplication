package com.example.test.Model

data class OrderItemProduct(
    var id: Int = 0,
    var name: String? = null,
    var price: String? = null,
    var weight: String? = null,
    var count : Int = 1,
    var isFocusableName : Boolean = false,
    var isFocusablePrice : Boolean = false,
    var isFocusableWeight : Boolean = false,
    var isFocusableCount : Boolean = false,

){
    companion object Factory{
        private var lastId: Int = -1
        fun make() : OrderItemProduct {
            lastId++
            return OrderItemProduct(
                id = lastId
            )
        }
    }
}