package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.githarefina.zwallet.R
import com.githarefina.zwallet.ui.adapter.TransactionAdapter
import com.githarefina.zwallet.databinding.FragmentHomeBinding
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.utils.KEY_USER_EMAIL
import com.githarefina.zwallet.utils.KEY_USER_TOKEN
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.utils.State
import com.githarefina.zwallet.viewmodel.HomeViewModel
import com.githarefina.zwallet.viewmodel.TransferViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var prefs: SharedPreferences
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterTransaction: TransactionAdapter
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: HomeViewModel by activityViewModels()

    var data = MutableLiveData<List<Invoice>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefs= activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter()
        TopUp()
        onClick()
//        profile()
    }

    fun onClick() {
        binding.imageUser.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.buttonTransfer.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_contactFragment)

        }
        binding.notif.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_notificationFragment)

        }
    }
    fun TopUp(){
        binding.buttonTopUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_topUpFragment)
        }
    }

    fun adapter() {

        this.adapterTransaction = TransactionAdapter(context, listOf())
        binding.transactionRecyclerView.apply {
            adapter = adapterTransaction
            layoutManager = LinearLayoutManager(context)

        }
        viewModel.getBalance().observe(viewLifecycleOwner, Observer {
            binding.nama.text = it.data?.data?.get(0)?.name.toString()
            binding.balance.text ="Rp "+it.data?.data?.get(0)?.balance.toString()
            binding.handphone.text = it.data?.data?.get(0)?.phone.toString()
//            if(binding.balance.text.toString()!=null){
//                binding.balance.formatPrice(it.data?.data?.get(0)?.balance.toString())
//
//            }else{
//                binding.balance.text= it.data?.data?.get(0)?.balance.toString()
//            }
        })

        viewModel.getInvoice().observe(viewLifecycleOwner, Observer {
            when (it.state) {
                State.LOADING -> {
                    binding.apply {
                        progressRecycler.visibility = View.VISIBLE
                        transactionRecyclerView.visibility = View.GONE
                    }
                }
                State.SUCCESS -> {
                    binding.apply {
                        progressRecycler.visibility = View.GONE
                        transactionRecyclerView.visibility = View.VISIBLE
                    }
                    if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                            this.adapterTransaction.apply {
                                addData(it.data.data!!)
                                notifyDataSetChanged()
                            }
                        }
                }
                State.ERROR -> {
                    binding.apply {
                        progressRecycler.visibility = View.GONE
                        transactionRecyclerView.visibility = View.GONE
                    }


                }
            }
        })
    }

}




