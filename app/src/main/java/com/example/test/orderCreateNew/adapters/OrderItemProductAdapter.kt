package com.example.test.orderCreateNew.adapters

import android.os.Build
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.orderCreateNew.diffCallback.OrderItemProductDiffCallback
import com.example.test.Model.OrderItemAddress
import com.example.test.Model.OrderItemProduct
import com.example.test.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.extensions.LayoutContainer
import kotlin.random.Random

class OrderItemProductAdapter() : ListAdapter<OrderItemProduct, OrderItemProductAdapter.ViewHolder>(
    OrderItemProductDiffCallback()
){
    private var listener : OrderItemProductChangeListener? = null
    private lateinit var address : OrderItemAddress


    constructor(address: OrderItemAddress, listener: OrderItemProductChangeListener?) : this(){
        this.listener = listener
        this.address = address
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return OrderItemProductHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_create_order, parent, false)
                .also {
                    it.setBackgroundColor(Random.nextInt())
                }
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract inner class ViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView : View?
            get() = itemView

        abstract fun bind(item: OrderItemProduct)
    }


    inner class OrderItemProductHolder(convertView : View): ViewHolder(convertView){
        private val tvTitle : TextView = itemView.findViewById(R.id.tv_title)
        private val ivClose : ImageView = itemView.findViewById(R.id.iv_close)
        private val tlProduct : TextInputLayout = itemView.findViewById(R.id.tl_product)
        private val tlPrice : TextInputLayout = itemView.findViewById(R.id.tl_price)
        private val tlWeight : TextInputLayout = itemView.findViewById(R.id.tl_weight)
        private val ivCountProductAdd : ImageView = itemView.findViewById(R.id.iv_count_product_add)
        private val tvCountProductValue : TextView = itemView.findViewById(R.id.tv_count_product_value)
        private val ivCountProductMinus : ImageView = itemView.findViewById(R.id.iv_count_product_minus)

        private var watcher: TextWatcher? = null

        override fun bind(item: OrderItemProduct) {
            tvTitle.text = "Груз №${adapterPosition + 1}"
            tlProduct.editText?.removeTextChangedListener(watcher)
            tlProduct.editText?.setTextIfChanged(item.name)
            this.watcher = tlProduct.editText?.doOnTextChanged { text, _, _, _ ->
                listener?.changeItemProductName(address, item, text.toString())
            }
// TODO Same for all editTexts
//            tlPrice.editText?.setText(item.price)
//            tlPrice.editText?.doOnTextChanged { text, _, _, _ ->
//                listener?.changeItemProduct(address, item.copy(price = text.toString()))
//            }
//            tlWeight.editText?.setText(item.weight)
//            tlWeight.isFocusable = true
//            tlWeight.editText?.setSelection(item.weight?.length?: 0)
//            tlWeight.editText?.doOnTextChanged { text, _, _, _ ->
//                listener?.changeItemProduct(address, item.copy(weight = text.toString()))
//            }
            tvCountProductValue.text = item.count.toString()


            if(Build.VERSION.SDK_INT >= 21){
                ivClose.setImageResource(R.drawable.ic_close_vector)
            }
            else{
                ivClose.setImageResource(R.drawable.ic_close)
            }

            ivClose.setOnClickListener{
                listener?.removeProduct(address, item)
            }
            ivCountProductAdd.setOnClickListener {
                listener?.changeItemProduct(address, item.copy(count = item.count + 1))
            }
            ivCountProductMinus.setOnClickListener {
                listener?.changeItemProduct(address, item.copy(count = item.count - 1))
            }
        }

    }
}

fun EditText.setTextIfChanged(text: String?) {
    if (text == this.text?.toString()) return
    setText(text)
}

interface OrderItemProductChangeListener{
    fun changeItemProduct(address: OrderItemAddress, item : OrderItemProduct)
    fun changeItemProductName(address: OrderItemAddress, item : OrderItemProduct, newName : String)

    fun removeProduct(address: OrderItemAddress, item: OrderItemProduct)
}