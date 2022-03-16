package com.githarefina.zwallet

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.databinding.FragmentDetailTransferBinding
import com.githarefina.zwallet.viewmodel.HomeViewModel
import com.githarefina.zwallet.viewmodel.TransferViewModel
import com.githarefina.zwallet.widget.LoadingDialog

class DetailTransferFragment : Fragment() {
    private lateinit var binding: FragmentDetailTransferBinding
    private val viewModel: TransferViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailTransferBinding.inflate(inflater,container,false)
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.button4.setOnClickListener {
            loadingDialog.start("Your Process Being Loaded")
            AlertDialog.Builder(context)
                .setTitle("Logout")
                .setMessage("Are you sure want to transer")
                .setPositiveButton("Yes") { _, _ ->
                    Navigation.findNavController(it).navigate(R.id.action_detailTransferFragment_to_confirmationPinFragment)
                    loadingDialog.stop()

                }.setNegativeButton("No") { _, _ ->
                    return@setNegativeButton
                    loadingDialog.stop()
                }.show()
        }

    }
    fun getData(){
        viewModel.data_transfer.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity,it.createdAt.toString(), Toast.LENGTH_LONG).show()
            binding.amount.text=it?.amount.toString()
            binding.date.text = it?.createdAt.toString()
            binding.note.text=it?.notes.toString()
        })
        viewModel.balance().observe(viewLifecycleOwner, Observer {
            binding.balance.text=it.data?.data?.get(0)?.balance.toString()
        })
        viewModel.data_contact.observe(viewLifecycleOwner, Observer {
            binding.textName.text = it.name
            binding.textphoneNumber.text = it.phone
        })

    }
}