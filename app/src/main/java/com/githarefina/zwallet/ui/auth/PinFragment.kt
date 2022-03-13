package com.githarefina.zwallet.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githarefina.zwallet.databinding.FragmentPinBinding

class PinFragment : Fragment() {
    private lateinit var binding :FragmentPinBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinBinding.inflate(inflater,container,false)
        return binding.root

    }


}