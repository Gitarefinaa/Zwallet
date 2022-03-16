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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.data.model.request.SignUpRequest
import com.githarefina.zwallet.data.model.response.ApIResponses
import com.githarefina.zwallet.databinding.FragmentRegisterBinding
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.utils.*
import com.githarefina.zwallet.viewmodel.HomeViewModel
import com.githarefina.zwallet.viewmodel.SignUpViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection
import kotlin.math.sign
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var pref :SharedPreferences
    private lateinit var loadingDialog: LoadingDialog
    private  val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        pref = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        loadingDialog= LoadingDialog(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }
    fun onClick(){
        binding.forgot.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_registerFragment_to_resetPasswordEmail)

        }
        binding.buttonSignUp.setOnClickListener {
            signUp(it)
        }
    }


        fun signUp(view: View) {
            var dialog = ProgressDialog(activity)
            dialog.setMessage("Loading")
            dialog.setTitle("OTP")
            pref.edit {
                putString(KEY_SIGNUP_EMAIL,binding.editTextTextPersonName.text.toString())
                apply()
            }
            viewModel.signUp(binding.editTextTextPersonName.text.toString(),
                binding.editTextTextPassword.text.toString(),binding.user.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    when (it.state) {
                        State.LOADING -> {
                            loadingDialog.start("Processing your request")
                        }


                        State.SUCCESS -> {
                            loadingDialog.stop()

                            if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                                loadingDialog.start("Processing your request")
                                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_otpFragment)

                                with(pref.edit()) {
                                    putString(KEY_SIGNUP_EMAIL, binding.editTextTextPersonName.text.toString()
                                    )
                                }
                            }else if(it.data?.status == HttpsURLConnection.HTTP_OK){
                                loadingDialog.start("Register gagal")


                            }
                            else {
                                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                            }
                        }


                        State.ERROR -> {
                            loadingDialog.stop()
                        } } })

        }





}