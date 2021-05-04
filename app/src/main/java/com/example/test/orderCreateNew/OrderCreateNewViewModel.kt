package com.example.test.orderCreateNew

import android.util.Log
import com.example.test.Model.OrderItemAddress
import com.example.test.Model.OrderItemProduct
import com.example.test.BaseViewModel

class OrderCreateNewViewModel() :
    BaseViewModel<OrderCreateNewState>(OrderCreateNewState()) {

    fun changeVisibilityAddressOption(item: OrderItemAddress){
        Log.d("OrderCreateViewModel", "change visibility address option")
        currentState.orderCreateModel.addresses = currentState.orderCreateModel.addresses.map {
            if(it.id == item.id){
                it.copy(isVisibleOptional = !it.isVisibleOptional)
            } else it
        }
        state.value = currentState

    }

    fun addItemProduct(item: OrderItemAddress) {
        currentState.orderCreateModel.addresses = currentState.orderCreateModel.addresses.map{
            if(it.id == item.id){
                var newProducts = it.products
                newProducts = newProducts.plus(OrderItemProduct.make())
                it.copy(products = newProducts)
            } else it
        }
        state.value = currentState
        Log.d("State", state.value.toString())
    }

    fun changeItemProduct(address: OrderItemAddress, item: OrderItemProduct) {
        currentState.orderCreateModel.addresses = currentState.orderCreateModel.addresses.map{ itemAddress ->
            if(itemAddress.id == address.id){
                itemAddress.copy(products = itemAddress.products.mapIndexed{ index, orderItemProduct ->
                    if(orderItemProduct.id == item.id){
                        orderItemProduct.copy(
                            id = item.id,
                            name = item.name,
                            price = item.price,
                            weight = item.weight,
                            count = item.count,

                        )
                    } else
                        orderItemProduct
                })
            }
            else itemAddress
        }
        state.value = currentState
        Log.d("State", state.value.toString())
    }

    fun changeItemProductName(address: OrderItemAddress, item: OrderItemProduct, newName: String) {

        currentState.orderCreateModel.addresses = currentState.orderCreateModel.addresses.map{ itemAddress ->
            if(itemAddress.id == address.id){
                itemAddress.copy(products = itemAddress.products.map{ orderItemProduct ->
                    if(orderItemProduct.id == item.id){

                        orderItemProduct.copy(
                            name = newName
                        )
                    } else
                        orderItemProduct
                })
            }
            else itemAddress
        }
        state.value = currentState


        Log.d("State", state.value.toString())
    }

    fun addAddress() {
        currentState.orderCreateModel = currentState.orderCreateModel.copy(addresses = currentState.orderCreateModel.addresses.plus(OrderItemAddress.make()))


        state.value = currentState
    }


}