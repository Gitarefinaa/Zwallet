package com.githarefina.zwallet.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.githarefina.zwallet.adapter.TransactionAdapter
import com.githarefina.zwallet.databinding.FragmentHomeBinding
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.utils.Helper.formatPrice
import com.githarefina.zwallet.utils.KEY_USER_TOKEN
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.viewmodel.HomeViewModel
import javax.net.ssl.HttpsURLConnection


class HomeFragment : Fragment() {
    private  lateinit var prefs:SharedPreferences
    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapterTransaction: TransactionAdapter
    private  val viewModel: HomeViewModel by viewModelFactory { HomeViewModel(requireActivity().application) }
    var data = MutableLiveData<List<Invoice>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs=activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        adapter()
        onClick()
//        profile()
    }

    fun onClick(){
        binding.imageUser.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_profileFragment)
//
        AlertDialog.Builder(context)
            .setTitle("Logout")
            .setMessage("Are you sure want to log out")
            .setPositiveButton("Yes"){_,_ ->
                with(prefs.getBoolean(KEY_USER_TOKEN,false)){
                    val intent = Intent(activity, SplashScreen::class.java)
                    startActivity(intent)
                    activity?.finish()
                }

            }.setNegativeButton("No"){_,_->
                return@setNegativeButton

            }
            }

        }
//    fun getBalance(){
//        NetworkConfig(requireContext()).buildAPI().getBalance().enqueue(object :Callback<APIResponse<ArrayList<Balance>>>{
//            override fun onResponse(
//                call: Call<APIResponse<ArrayList<Balance>>>,
//                response: Response<APIResponse<ArrayList<Balance>>>
//            ) {
//                binding.balance.text = response.body()?.data?.get(0)?.balance.toString()
//                binding.handphone.text= response.body()?.data?.get(0)?.phone.toString()
//            }
//
//            override fun onFailure(call: Call<APIResponse<ArrayList<Balance>>>, t: Throwable) {
//                Toast.makeText(requireContext(),"gagal",Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//

//    fun profile(){
//        NetworkConfig(requireContext()).buildAPI().getProfile().enqueue(object :Callback<APIResponse<UserDetail>>{
//            override fun onResponse(
//                call: Call<APIResponse<UserDetail>>,
//                response: Response<APIResponse<UserDetail>>
//            ) {
//                binding.nama.text=response.body()?.data?.firstname
//            }
//
//            override fun onFailure(call: Call<APIResponse<UserDetail>>, t: Throwable) {
//                Toast.makeText(requireContext(),"gagal",Toast.LENGTH_LONG).show()
//            }
//        })
//    }

//    fun prepareData():List<Invoice> {
        //       viewModel.getDataInvoice().observe(viewLifecycleOwner, Observer{
//             data.postValue(it.data!!)
//       })
//        return data.value!!
//    }
        fun adapter() {
            this.adapterTransaction = TransactionAdapter(context, listOf())
            binding.transactionRecyclerView.apply {
                adapter = adapterTransaction
                layoutManager = LinearLayoutManager(context)

            }
        viewModel.getBalance().observe(viewLifecycleOwner, Observer {
            binding.balance.formatPrice(it.data?.get(0)?.balance.toString())
        })
            viewModel.getInvoice().observe(viewLifecycleOwner, Observer {
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
            if (it.status == HttpsURLConnection.HTTP_OK){
                this.adapterTransaction.apply {
                    addData(it.data!!)
                    notifyDataSetChanged()
                }
            }else{
                Toast.makeText(activity,"gagal",Toast.LENGTH_LONG).show()
            }
            })
        }

    }




