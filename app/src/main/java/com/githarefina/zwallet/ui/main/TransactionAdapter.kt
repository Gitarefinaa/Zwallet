package com.githarefina.zwallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githarefina.zwallet.R

import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.utils.*
import com.githarefina.zwallet.utils.Helper.formatPrice
import com.google.android.material.imageview.ShapeableImageView

class TransactionAdapter(val context: Context?, private var data: List<Invoice>): RecyclerView.Adapter<TransactionAdapter.TransactionAdapterHolder>(){
    //
    class TransactionAdapterHolder(view:View): RecyclerView.ViewHolder(view) {
        var transactionName: TextView
        var transactionType: TextView
        var transactionImage:ShapeableImageView
        var transactionLayout:LinearLayout
        var amount :TextView


        init {
            transactionImage = view.findViewById(R.id.transactionImage)
            transactionName=view.findViewById(R.id.transactionName)
            transactionType = view.findViewById(R.id.transactionType)
            transactionLayout=view.findViewById(R.id.layout_contact)
            amount=view.findViewById(R.id.transactionNominal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapterHolder {
        return TransactionAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_transfer, parent, false))
    }

    override fun onBindViewHolder(holder:TransactionAdapterHolder , position: Int) {
        var model = data.get(position)
        holder.transactionName.text = model.name
        holder.transactionType.text = model.type
        holder.amount.formatPrice(model.amount.toString())


        Glide.with(holder.transactionImage).load(BASE_URL +model.image)
            .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.img))
            .into(holder.transactionImage)
        holder.transactionLayout.setOnClickListener {
            var pref = context?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            pref?.edit()?.putInt(KEY_ID_TRANSACTION,model.id!!)

        }
    }
    override fun getItemCount(): Int {
        return  this.data.size
    }
    fun addData(data:List<Invoice>){
        this.data =data
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if (charSearch.isEmpty()) {
//                    contactList = data
//                } else {
//                    val resultList = ArrayList<ContactModel>()
//                    for (row in contactList) {
//                        if (row.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
//                            resultList.add(row)
//                        }
//                    }
//                    contactList = data
//                }
//                val filterResults = FilterResults()
//                filterResults.values = contactList
//                return filterResults
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                contactList = results?.values as ArrayList<ContactModel>
//                notifyDataSetChanged()
//            }
//
//        }
//    }


}