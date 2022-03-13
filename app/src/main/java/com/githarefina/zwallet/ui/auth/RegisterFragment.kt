package com.githarefina.zwallet.ui.auth

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.data.model.request.SignUpRequest
import com.githarefina.zwallet.data.model.response.ApIResponses
import com.githarefina.zwallet.databinding.FragmentRegisterBinding
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.KEY_SIGNUP_EMAIL
import com.githarefina.zwallet.utils.PREFS_NAME
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var pref :SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        pref = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }
       fun onClick(){
           binding?.buttonSignUp
           .setOnClickListener {
            signUp(it)
           }
       }

        fun signUp(view: View) {
            var dialog = ProgressDialog(activity)
            dialog.setMessage("Loading")
            dialog.setTitle("OTP")
            var email= binding.editTextTextPersonName.text.toString()
            pref.edit {
                putString(KEY_SIGNUP_EMAIL,binding.editTextTextPersonName.text.toString())
                apply()
            }


            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_otpFragment)
            var request = SignUpRequest(email = binding.editTextTextPersonName.text.toString(), password = binding.editTextTextPassword.text.toString(), username = binding.user.text.toString())
            NetworkConfig(requireContext()).buildAPI().signup(request).enqueue(object : Callback<ApIResponses>{
                override fun onResponse(call: Call<ApIResponses>, response: Response<ApIResponses>) {
                    if(response.body()?.status !=HttpsURLConnection.HTTP_OK){
                        Toast.makeText(activity,response.body()?.message.toString()+email,Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(activity,response.body()?.message.toString()+email,Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ApIResponses>, t: Throwable) {
                    Toast.makeText(activity,t.localizedMessage,Toast.LENGTH_LONG).show()
                }
            })
            binding.forgot.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_registerFragment_to_resetPasswordEmail)

            }
            binding.buttonSignUp.setOnClickListener {
//                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeActivity)


            }




    }
}