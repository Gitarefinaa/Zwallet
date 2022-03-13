package com.githarefina.zwallet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentPersonalInformationBinding

class PersonalInformationFragment : Fragment() {

private lateinit var binding : FragmentPersonalInformationBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalInformationBinding.inflate(inflater,container,false)
        return binding.root
    }






}