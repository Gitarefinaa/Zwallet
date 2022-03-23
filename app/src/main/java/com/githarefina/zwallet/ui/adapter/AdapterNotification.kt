package com.githarefina.zwallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.githarefina.zwallet.R
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.databinding.AdapterNotificationBinding
import com.githarefina.zwallet.utils.Helper.formatPrice
import com.githarefina.zwallet.viewmodel.NotificationViewModel

class AdapterNotification(val context: Context?, private var data: List<Invoice>?, var viewModel: NotificationViewModel, private val viewLifecycleOwner: LifecycleOwner, ): RecyclerView.Adapter<AdapterNotification.NotificationAdapterHolder>(){
    class NotificationAdapterHolder(val binding:AdapterNotificationBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(
            position: Int,
            data: List<Invoice>,
            viewModel: NotificationViewModel,
            view: View,
            viewLifecycleOwner: LifecycleOwner
        ) {
            var model = data.get(position)
            viewModel.getDataTransaction(model.id!!).observe(viewLifecycleOwner, Observer {
                var sender = it.data?.data?.sender
                var receiver = it.data?.data?.receiver
                if (model.type.equals("out")) {
                    binding.transactionImage.setImageResource(R.drawable.icon_to)
                    binding.transactionName.text = "transfered to " + receiver
                    if (model.notes.equals("Top Up Balance")) {
                        binding.transactionName.text = "Top Up Balance to" + receiver
                    }

                } else {
                    binding.transactionImage.setImageResource(R.drawable.icon_from)
                    binding.transactionName.text = "transfered from " + sender


                }
                binding.transactionType.formatPrice(model.amount.toString())

            })
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapterHolder {
        var binding = AdapterNotificationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder:NotificationAdapterHolder , position: Int) {
        holder.bind(position, data!!,viewModel,holder.itemView,viewLifecycleOwner)

    }
    override fun getItemCount(): Int {
    return  this.data!!.size
    }
    fun addData(data:List<Invoice>){
        this.data =data
    }

//    override fun getFilter(): Filter {
//            return object:Filter(){
//                override fun performFiltering(p0: CharSequence?): FilterResults {
//                    val searchString: String = p0.toString()
//
//                    if (searchString.isEmpty()) {
//                        data = filteredUserList!!
//                    } else {
//                        val tempFilteredList: ArrayList<ContactModel> = ArrayList()
//                        for (user in filteredUserList!!) {
//
//                            // search for user title
//                            if (user.name?.toLowerCase()?.contains(searchString)!!) {
//                                tempFilteredList.add(user)
//                            }
//                        }
//                        filteredUserList = tempFilteredList
//                    }
//
//                    val filterResults = FilterResults()
//                    filterResults.values = filteredUserList
//                    return filterResults
//                }
//
//                override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//                    filteredUserList =p1?.values as ArrayList<ContactModel>
//                    notifyDataSetChanged()
//                }
//            }
//
//
//    }

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