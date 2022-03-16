package com.githarefina.zwallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.githarefina.zwallet.databinding.AdapterTopUpBinding

class TopUpAdapter(val context: Context?, private var data: List<String>): RecyclerView.Adapter<TopUpAdapter.TransactionAdapterHolder>(){
    private lateinit var binding:AdapterViewBindingAdapter
    class TransactionAdapterHolder(binding:AdapterTopUpBinding): RecyclerView.ViewHolder(binding.root) {
        var transactionId= binding.transactionId
        var transactionName= binding.transactionName

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapterHolder {
        val binding = AdapterTopUpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder:TransactionAdapterHolder , position: Int) {
        var pos = position+1
        holder.transactionId.text= pos.toString()
        holder.transactionName.text= data.get(position)

    }
    override fun getItemCount(): Int {
    return  this.data.size
    }
    fun addData(data:List<String>){
        this.data =data
    }


}