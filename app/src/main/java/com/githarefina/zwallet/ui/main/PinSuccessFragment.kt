package com.githarefina.zwallet.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentHomeBinding
import com.githarefina.zwallet.databinding.FragmentPinSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PinSuccessFragment : Fragment() {
    private lateinit var binding: FragmentPinSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPinSuccessBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        success()
    }
    fun success(){
        binding.button.setOnClickListener {
          var intent = Intent(activity,MainActivity::class.java)
          startActivity(intent)
          activity?.finish()

        }
    }
}
