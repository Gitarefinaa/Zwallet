package com.githarefina.zwallet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentTransferSuccessBinding
import com.githarefina.zwallet.viewmodel.TransferViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentTransferSuccess : Fragment() {
    private lateinit var binding:FragmentTransferSuccessBinding

    private val viewModel: TransferViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTransferSuccessBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data()
        binding.button4.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragmentTransferSuccess_to_homeFragment)
        }
    }


    fun data(){
        viewModel.data_contact.observe(viewLifecycleOwner, Observer {
            binding.textName.text = it.name
            binding.textphoneNumber.text = it.phone
        })
        viewModel.data_transfer.observe(viewLifecycleOwner, Observer {
        Toast.makeText(activity,it.createdAt.toString(),Toast.LENGTH_LONG).show()
        binding.amount.text=it?.amount.toString()
        binding.date.text = it?.createdAt.toString()
        binding.note.text=it?.notes.toString()
        })
        viewModel.balance().observe(viewLifecycleOwner, Observer {
            binding.balance.text=it.data?.data?.get(0)?.balance.toString()
        })
//        var model = viewModel.data_transfer.value
//        binding.amount.text=model?.amount.toString()
//        var balanceLeft = model?.amount.toString()
//        binding.date.text = model?.createdAt.toString()
//        binding.note.text=model?.notes.toString()
    }
}