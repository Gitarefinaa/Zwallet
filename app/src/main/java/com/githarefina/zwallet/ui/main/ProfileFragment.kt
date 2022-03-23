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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentProfileBinding
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.utils.KEY_USER_TOKEN
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.viewmodel.ProfilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var prefs:SharedPreferences
    private  val viewModel: ProfilViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        getProfile()
        onclick()
    }
    fun onclick(){
        binding.logout.setOnClickListener {
            logout()
        }

        }
    fun logout(){

        AlertDialog.Builder(context)
            .setTitle("Logout")
            .setMessage("Are you sure want to log out")
            .setPositiveButton("Yes") { _, _ ->
                with(prefs.edit().putBoolean(KEY_USER_TOKEN, false)) {
                    val intent = Intent(activity, SplashScreen::class.java)
                    startActivity(intent)
                    activity?.finish()
                }

            }.setNegativeButton("No") { _, _ ->
                return@setNegativeButton
            }.show()
    }


    fun getProfile(){
        viewModel.getProfile().observe(viewLifecycleOwner, Observer {
            binding.phoneNumber.text=it.data?.data?.phone
            binding.textName.text =it.data?.data?.firstname +it.data?.data?.lastname
        })
        binding.notification.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_personalInformationFragment)

        }

        binding.personalInformation.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_personalInformationFragment)
        }
        binding.changePin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_changePinFragment)
        }
        binding.changePassword.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_changePasswordFragment)
        }
        binding.phoneNumber.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_personalInformationFragment)
        }


    }
}