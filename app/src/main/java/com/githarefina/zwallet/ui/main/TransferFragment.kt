package com.githarefina.zwallet.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentTransferBinding
import com.githarefina.zwallet.utils.KEY_USER_TOKEN
import com.githarefina.zwallet.viewmodel.TransferViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class TransferFragment : Fragment(){
    private lateinit var binding:FragmentTransferBinding
    private val model: TransferViewModel by activityViewModels()
    private lateinit var  loadingDialog:LoadingDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTransferBinding.inflate(inflater,container,false)
        loadingDialog= LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.data_contact.observe(viewLifecycleOwner, Observer {
            binding.textName.text = it.name
            binding.textphoneNumber.text = it.phone
        })
        model.balance().observe(viewLifecycleOwner, Observer {
            binding.balance.text=it.data?.data?.get(0)?.balance.toString()

        })


        binding.transfer.setOnClickListener {
            loadingDialog.start("Process being loaded")
            getBalance()
            AlertDialog.Builder(context)
                .setTitle("Process your request")
                .setMessage("Are you sure you want to continue ?")
                .setPositiveButton("Yes") { _, _ ->
                    Navigation.findNavController(view).navigate(R.id.action_transferFragment_to_detailTransferFragment)
                    loadingDialog.stop()

                }.setNegativeButton("No") { _, _ ->
                    return@setNegativeButton
                    loadingDialog.stop()
                }.show()
        }
    }

    fun getBalance() {
        var amount = binding.amountPrice.text.toString()
        if(amount.isNullOrEmpty()){
            model.setAmount(0)
        }else{
            model.setAmount(amount.toInt())
        }
           model.notes(binding.notes.text.toString())



    }


}