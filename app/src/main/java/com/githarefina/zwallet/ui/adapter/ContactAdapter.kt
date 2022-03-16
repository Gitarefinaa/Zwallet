package com.githarefina.zwallet.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githarefina.zwallet.R
import com.githarefina.zwallet.data.model.ContactModel
import com.githarefina.zwallet.databinding.AdapterContactBinding
import com.githarefina.zwallet.utils.BASE_URL
import com.githarefina.zwallet.viewmodel.TransferViewModel


class ContactAdapter(
    val context: Context?, private var data: ArrayList<ContactModel>,
    val viewModel: TransferViewModel
): RecyclerView.Adapter<ContactAdapter.TransactionAdapterHolder>(){

    private var mOnItemClickListener: OnClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    class OnClickListener(val clickListener: (model: ContactModel) -> Unit) {
        fun onClick(view: View, model: ContactModel) = clickListener(model)
    }
    class TransactionAdapterHolder(val binding:AdapterContactBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(
            position: Int,
            data: ArrayList<ContactModel>,
            viewModel: TransferViewModel, view: View, mOnItemClickListener: OnClickListener?
        ){
        var row_index:Int = -1
        var model = data.get(position)
        binding.transactionName.text = model.name
        binding.transactionType.text = model.phone
        binding.root.setOnClickListener{
            mOnItemClickListener?.onClick(view, model);

        }
        Glide.with(binding.transactionImage).load(BASE_URL + model.image)
            .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.img))
            .into(binding.transactionImage)
        binding.root.setOnClickListener{
            it.findNavController().navigate(R.id.action_contactFragment_to_transferFragment)
                viewModel.receiver(model.id)
                viewModel.setContact(model)
            if (row_index == position) {
                    binding.root.setBackgroundResource(R.drawable.background_header_transaction)


                } else {
                    binding.root.setBackgroundResource(R.drawable.background_item_transaction_home)
                }
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapterHolder {
        var binding = AdapterContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransactionAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder:TransactionAdapterHolder , position: Int) {
        holder.bind(position,data,viewModel,holder.itemView,mOnItemClickListener)

    }
    override fun getItemCount(): Int {
    return  this.data.size
    }
    fun addData(data:ArrayList<ContactModel>){
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