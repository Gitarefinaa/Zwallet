package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentPersonalInformationBinding
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.viewmodel.ProfilViewModel
import kotlin.coroutines.Continuation

class PersonalInformationFragment : Fragment() {

private lateinit var binding : FragmentPersonalInformationBinding
private  val viewModel: ProfilViewModel by viewModelFactory { ProfilViewModel(requireActivity().application) }
private lateinit var  prefs :SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalInformationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs= activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        getProfile()
    }
    fun getProfile(){
        viewModel.getProfile().observe(viewLifecycleOwner, Observer {
            binding.firstName.text=it.data?.data?.firstname
            binding.lastName.text = it.data?.data?.lastname
            binding.verifiedEmail.text =it.data?.data?.email
            binding.phoneNumber.text=it.data?.data?.phone
        })
    }

}