package com.example.nikeshop.feature.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.common.formatPrice
import com.example.nikeshop.common.implementSpringAnimationTrait
import com.example.nikeshop.data.Product
import com.example.nikeshop.service.ImageLoadingService
import com.example.nikeshop.view.NikeImageView

class ProductListAdapter(val imageLoadingService: ImageLoadingService): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var productOnClickListener: ProductOnClickListener? = null
    var products = ArrayList<Product>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleTv:TextView = itemView.findViewById(R.id.productTitleTv)
        val productIv: NikeImageView = itemView.findViewById(R.id.productIv)
        val currentPriceTv:TextView = itemView.findViewById(R.id.currentPriceTv)
        val previousPriceTv:TextView = itemView.findViewById(R.id.previousPriceTv)

        fun bindProduct(product: Product){
            imageLoadingService.load(productIv, product.image)
            titleTv.text = product.title
            currentPriceTv.text = formatPrice(product.price)
            previousPriceTv.text = formatPrice(product.previous_price)
            previousPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {
                productOnClickListener?.onProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_prodcuct, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindProduct(products[position])

    override fun getItemCount(): Int  = products.size

    interface ProductOnClickListener{
        fun onProductClick(product:Product)
    }
}