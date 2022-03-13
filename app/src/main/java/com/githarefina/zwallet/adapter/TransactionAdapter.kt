package com.githarefina.zwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githarefina.zwallet.R
import com.githarefina.zwallet.data.api.model.Transaction
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.utils.BASE_URL
import com.githarefina.zwallet.utils.Helper
import com.githarefina.zwallet.utils.Helper.formatPrice
import com.google.android.material.imageview.ShapeableImageView

class TransactionAdapter(val context: Context?, private var data: List<Invoice>): RecyclerView.Adapter<TransactionAdapter.TransactionAdapterHolder>(){
    private lateinit var binding:AdapterViewBindingAdapter
    class TransactionAdapterHolder(view:View): RecyclerView.ViewHolder(view) {
        var transactionName: TextView
        var transactionType: TextView
        var transactionNominal :TextView
        var transactionImage:ShapeableImageView

        init {
            transactionImage = view.findViewById(R.id.transactionImage)
            transactionName=view.findViewById(R.id.transactionName)
            transactionType = view.findViewById(R.id.transactionType)
            transactionNominal = view.findViewById(R.id.transactionNominal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapterHolder {
        return TransactionAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_subscription, parent, false)) }

    override fun onBindViewHolder(holder:TransactionAdapterHolder , position: Int) {
     var model = data.get(position)
     var drawable = model?.image
     holder.transactionName.text = model.name
     holder.transactionType.text = model.type
     Glide.with(holder.transactionImage).load(BASE_URL +model.image)
         .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.img))
         .into(holder.transactionImage)
     holder.transactionNominal.formatPrice(model?.amount.toString())
    }
    override fun getItemCount(): Int {
    return  this.data.size
    }
    fun addData(data:List<Invoice>){
        this.data =data
    }


}