package com.example.test.orderCreateNew

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.orderCreateNew.adapters.OrderItemAddressAdapter
import com.example.test.orderCreateNew.adapters.OrderItemAddressChangeListener
import com.example.test.orderCreateNew.adapters.OrderItemProductChangeListener
import com.example.test.Model.OrderItemAddress
import com.example.test.Model.OrderItemProduct
import com.example.test.R
import com.google.android.material.button.MaterialButton

class OrderCreateNewFragment : Fragment() {
    private lateinit var viewModel: OrderCreateNewViewModel
    private lateinit var rvAddress : RecyclerView
    private lateinit var btnAddAddress :  MaterialButton
    private lateinit var adapterAddress : OrderItemAddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_create_order_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = OrderCreateNewInjection.provide()
        viewModel = ViewModelProvider(this, viewModelFactory).get(OrderCreateNewViewModel::class.java)
        initViewModel()

        btnAddAddress = view.findViewById(R.id.btn_add_address)
        btnAddAddress.setOnClickListener{
            viewModel.addAddress()
        }
        rvAddress = view.findViewById(R.id.rv_address)

        adapterAddress = OrderItemAddressAdapter(object : OrderItemAddressChangeListener{
            override fun changeOptionalVisibility(item: OrderItemAddress) {
                viewModel.changeVisibilityAddressOption(item)
            }

            override fun addItemProduct(item: OrderItemAddress) {
                viewModel.addItemProduct(item)
            }

        },
            object : OrderItemProductChangeListener{

                override fun changeItemProduct(address: OrderItemAddress, item: OrderItemProduct) {
                    viewModel.changeItemProduct(address, item)
                }

                override fun changeItemProductName(
                    address: OrderItemAddress,
                    item: OrderItemProduct,
                    newName: String
                ) {
                    viewModel.changeItemProductName(address, item, newName)
                }

                override fun removeProduct(address: OrderItemAddress, item: OrderItemProduct) {

                }

            }
        )

        with(rvAddress){
            layoutManager = LinearLayoutManager(application)
            adapter = adapterAddress
            isNestedScrollingEnabled = true
        }



    }

    private fun initViewModel() {
        viewModel.observeState(viewLifecycleOwner){ it ->
            Log.d("OrderList state", it.toString())
//            val test = it.addresses
//            test.add(OrderItemAddress.make())

//            adapterAddress.submitList(null)
            adapterAddress.submitList(it.orderCreateModel.addresses)
//            adapterAddress.submitList(it.orderCreateModel.addresses.map{ orderItemAddress ->
//                orderItemAddress.copy()
//            })
//            adapterAddress.submitList(it.addresses)
        }
    }
}