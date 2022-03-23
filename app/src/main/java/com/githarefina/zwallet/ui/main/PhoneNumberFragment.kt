package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentPhoneNumberBinding
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.utils.State
import com.githarefina.zwallet.viewmodel.ProfilViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import com.google.gson.JsonObject
import javax.net.ssl.HttpsURLConnection

class PhoneNumberFragment : Fragment() {
    private lateinit var binding: FragmentPhoneNumberBinding
    private lateinit var prefs: SharedPreferences
    private lateinit  var loadingDialog: LoadingDialog
    private  val viewModel: ProfilViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentPhoneNumberBinding.inflate(inflater,container,false)
        prefs = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changePhoneNumber()
    }


    fun changePhoneNumber(){
        binding.confirm.setOnClickListener {view->
            val phoneNumber = JsonObject()
            phoneNumber.addProperty("phone", binding.idPhone.text.toString() + binding.phoneNumber.text.toString())


            viewModel.changeInfo(phoneNumber).observe(viewLifecycleOwner, Observer {
                when (it.state) {
                    State.LOADING -> {
                        loadingDialog.start("Processing your request")
                    }
                    State.ERROR -> {
                        loadingDialog.stop()
                    }
                    State.SUCCESS -> {
                        if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                            loadingDialog.stop()
                            Navigation.findNavController(view).popBackStack()
                        }
                    }

                }
            })
        }

    }
}