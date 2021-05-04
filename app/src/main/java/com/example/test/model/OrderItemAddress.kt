package com.example.test.Model

data class OrderItemAddress(
    val id : Int = 0,
    val number : String? = null,
    val user : String? = null,
    val comment: String? = null,
    var isVisibleOptional : Boolean = false,
    var products : List<OrderItemProduct> = listOf()
){
    companion object Factory{
        private var lastId: Int = -1
        fun make() : OrderItemAddress {
            lastId++
            return OrderItemAddress(
                id = lastId
            )
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}