package com.githarefina.zwallet.ui.auth

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentLoginBinding
import com.githarefina.zwallet.ui.main.MainActivity
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.utils.*
import com.githarefina.zwallet.viewmodel.LoginViewModel
import javax.net.ssl.HttpsURLConnection
class FragmentLogin : Fragment() {
    private  val viewModel: LoginViewModel by viewModelFactory { LoginViewModel(requireActivity().application) }
    private lateinit var binding:FragmentLoginBinding
    private lateinit var prefs :SharedPreferences
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        prefs = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application : Application = context?.applicationContext as Application
        loginClick()
    }


    fun toMain(){
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
    fun observeData() {
        if(binding.editTextTextPersonName.text.isNullOrEmpty()||binding.editTextTextPassword.text.isNullOrEmpty()){
            Toast.makeText(activity,"email/password is empty",Toast.LENGTH_LONG).show()
        }
        val response = viewModel.login(
            binding.editTextTextPersonName.text.toString(),
            binding.editTextTextPassword.text.toString()
        )
        response.observe(viewLifecycleOwner) {
            if (it.status == HttpsURLConnection.HTTP_OK) {
                with(prefs.edit()) {
                    putBoolean(KEY_LOGGED_IN, true)
                    putString(KEY_USER_EMAIL, it.data?.email)
                    putString(KEY_USER_TOKEN, it.data?.token)
                    putString(KEY_USER_REFRESH_TOKEN, it.data?.refreshToken)
                    apply()
                }
                toMain()
            } else {
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loginClick(){
        binding.textSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.loginActionRegister)
        }
        binding.buttonLogin.setOnClickListener {
            observeData()

        }
        binding.forgot.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_resetPasswordEmail)
        }
    }
}
