package com.githarefina.zwallet.ui.auth

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentRegisterBinding
import com.githarefina.zwallet.utils.*
import com.githarefina.zwallet.viewmodel.SignUpViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection
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
            pref.edit().putString(KEY_SIGNUP_EMAIL,binding.email.text.toString()).apply()}
    }



        fun signUp(view: View) {
            var dialog = ProgressDialog(activity)
            dialog.setMessage("Loading")
            dialog.setTitle("OTP")
            viewModel.signUp( username = binding.user.text.toString(),email = binding.email.text.toString(), password = binding.password.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    when (it.state) {
                        State.LOADING -> {
                            loadingDialog.start("Processing your request")
                        }
                        State.SUCCESS -> {
                            loadingDialog.stop()
                            if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_otpFragment)
                            }else if(it.data?.status == 401){
                                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_otpFragment)
                            }
                            else {
                            }
                        }


                        State.ERROR -> {
                            loadingDialog.stop()
                        }
                    }
                })

        }





}