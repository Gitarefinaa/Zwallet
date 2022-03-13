package com.githarefina.zwallet.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentResetPasswordBinding
import com.githarefina.zwallet.databinding.FragmentResetPasswordEmailBinding


class ResetPasswordEmail : Fragment() {
    private lateinit var binding: FragmentResetPasswordEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordEmailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetPassword()
    }


    fun resetPassword(){
        binding.buttonConfirm.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_resetPasswordEmail_to_resetPassword)
        }
    }
}