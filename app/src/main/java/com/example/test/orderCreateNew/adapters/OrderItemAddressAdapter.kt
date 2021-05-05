package com.example.test.orderCreateNew.adapters

import android.os.Build
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.orderCreateNew.adapters.OrderItemProductChangeListener
import com.example.test.orderCreateNew.diffCallback.OrderItemAddressDiffCallback
import com.example.test.Model.OrderItemAddress
import com.example.test.Model.OrderItemProduct
import com.example.test.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.extensions.LayoutContainer

class OrderItemAddressAdapter() : ListAdapter<OrderItemAddress, OrderItemAddressAdapter.ViewHolder>(OrderItemAddressDiffCallback()){
    private var listener : OrderItemAddressChangeListener? = null
    private var listenerProduct : OrderItemProductChangeListener? = null

    constructor(listener: OrderItemAddressChangeListener, listenerProduct : OrderItemProductChangeListener) : this(){
        this.listener = listener
        this.listenerProduct = listenerProduct
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return OrderItemAddressHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_address_delivery, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        Log.d("onBindViewHolder", getItem(position).toString())
    }

    abstract inner class ViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView : View?
            get() = itemView

        abstract fun bind(item: OrderItemAddress)
    }


    inner class OrderItemAddressHolder(convertView : View): ViewHolder(convertView){
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tlAddress : TextInputLayout = itemView.findViewById(R.id.tl_address)
        private val tlNumber : TextInputLayout = itemView.findViewById(R.id.tl_number)
        private val tvAddressOptional : TextView = itemView.findViewById(R.id.tv_address_optional)
        private val ivExpandable : ImageView = itemView.findViewById(R.id.iv_expandable)
        private val llAddressOptional: LinearLayout = itemView.findViewById(R.id.ll_address_optional)
        private val tlUser: TextInputLayout = itemView.findViewById(R.id.tl_user)
        private val tlComment: TextInputLayout = itemView.findViewById(R.id.tl_user)
        private val tlMoneyOperation: TextInputLayout = itemView.findViewById(R.id.tl_money_operation)
        private val btnDateTime: Button = itemView.findViewById(R.id.btn_date_time)
        private val tvAddProduct: TextView = itemView.findViewById(R.id.tv_add_product)

        private val rvProduct : RecyclerView = itemView.findViewById(R.id.rv_product)

        private val ivClose : ImageView = itemView.findViewById(R.id.iv_close)



        override fun bind(item: OrderItemAddress) {
            if(adapterPosition == 0)
                tvTitle.text = "Откуда забрать?"
            else tvTitle.text = "Куда доставить $adapterPosition?"

            if(Build.VERSION.SDK_INT >= 21){
                ivClose.setImageResource(R.drawable.ic_close_vector)
            }
            else{
                ivClose.setImageResource(R.drawable.ic_close)
            }




            if(item.isVisibleOptional){
                llAddressOptional.visibility = View.VISIBLE
                if(Build.VERSION.SDK_INT >= 21){
                    ivExpandable.setImageResource(R.drawable.ic_hide_vector)
                }
                else{
                    ivExpandable.setImageResource(R.drawable.ic_hide)
                }
            }
            else{
                llAddressOptional.visibility = View.GONE
                if(Build.VERSION.SDK_INT >= 21){
                    ivExpandable.setImageResource(R.drawable.ic_expandable_vector)
                }
                else{
                    ivExpandable.setImageResource(R.drawable.ic_expandable)
                }
            }

            tvAddressOptional.setOnClickListener{
                listener?.changeOptionalVisibility(item)
            }
            ivExpandable.setOnClickListener{
                listener?.changeOptionalVisibility(item)
            }

            val currentAdapter = rvProduct.adapter as? OrderItemProductAdapter
            val resultAdapter = if(currentAdapter == null){
                rvProduct.layoutManager = LinearLayoutManager(itemView.context)
                rvProduct.isNestedScrollingEnabled = true
                val newAdapter = OrderItemProductAdapter(item, listenerProduct)
                rvProduct.adapter = newAdapter
                newAdapter
            } else{
                currentAdapter
            }

            Log.d("AdapterAddress", item.products.size.toString())
            resultAdapter.submitList(item.products)

            tvAddProduct.setOnClickListener {
                listener?.addItemProduct(item)
            }

        }
    }



}

interface OrderItemAddressChangeListener{
    fun changeOptionalVisibility(item: OrderItemAddress)
    fun addItemProduct(item: OrderItemAddress)

}