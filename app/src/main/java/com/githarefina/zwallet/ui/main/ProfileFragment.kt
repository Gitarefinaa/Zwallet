package com.githarefina.zwallet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.githarefina.zwallet.databinding.FragmentProfileBinding
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.viewmodel.ProfilViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private  val viewModel: ProfilViewModel by viewModelFactory { ProfilViewModel(requireActivity().application) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfile()
    }


    fun getProfile(){
        viewModel.getProfile().observe(viewLifecycleOwner, Observer {
            binding.phoneNumber.text=it.data?.phone
            binding.textName.text = it.data?.firstname +" "+it.data?.lastname
        })


    }
}