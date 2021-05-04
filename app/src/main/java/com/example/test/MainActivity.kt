package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test.orderCreateNew.OrderCreateNewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragmentBackStack(OrderCreateNewFragment(),"OrderCreate")
    }



    private fun loadFragmentBackStack(
        fragment: Fragment,
        tag: String
    ) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commitAllowingStateLoss()
    }
}