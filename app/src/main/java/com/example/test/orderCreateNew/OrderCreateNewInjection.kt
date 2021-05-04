package com.example.test.orderCreateNew



object OrderCreateNewInjection{
    fun provide(): OrderCreateViewModelFactory {
        return OrderCreateViewModelFactory()
    }
}