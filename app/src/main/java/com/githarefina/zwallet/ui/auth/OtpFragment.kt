package com.githarefina.zwallet.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentOtpBinding
import com.githarefina.zwallet.ui.viewModelFactory
import com.githarefina.zwallet.utils.*
import com.githarefina.zwallet.viewmodel.OTPViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class OtpFragment : Fragment() {
    private lateinit var binding :FragmentOtpBinding
    var otp  = mutableListOf<EditText>()
    private  lateinit var pref:SharedPreferences
    private lateinit  var loadingDialog: LoadingDialog

    private  val viewModel: OTPViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loadingDialog =LoadingDialog(requireActivity())
        binding = FragmentOtpBinding.inflate(inflater,container,false)
        pref = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEditText()
    }
    fun initEditText(){
        otp.add(0,binding.otp1)
        otp.add(1,binding.otp2)
        otp.add(2,binding.otp3)
        otp.add(3,binding.otp4)
        otp.add(4,binding.otp5)
        otp.add(5,binding.otp6)
        otpHandler()
    }
    fun otpHandler(){
        for (i in 0..5) { //Its designed for 6 digit OTP
            otp.get(i).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    otp.get(i).setBackgroundResource(R.drawable.background_edit_text_otp_after)

                }
                override fun afterTextChanged(s: Editable) {
                    if (i == 5 && !otp.get(i).getText().toString().isEmpty()) {
                        otp.get(i).clearFocus()

                    //Clears focus when you have entered the last digit of the OTP.
                    } else if (!otp.get(i).getText().toString().isEmpty()) {
                        otp.get(i + 1).requestFocus() //focuses on the next edittext after a digit is entered.
                    }
                }
            })
            otp.get(i).setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (event.action !== KeyEvent.ACTION_DOWN) {
                    return@OnKeyListener false //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                }
                if (keyCode == KeyEvent.KEYCODE_DEL && otp.get(i).getText().toString().isEmpty() && i != 0) {
                    //this condition is to handel the delete input by users.
                    otp.get(i - 1).setText("") //Deletes the digit of OTP
                    otp.get(i - 1).requestFocus()
                    otp.get(i - 1).setBackgroundResource(R.drawable.background_edit_text_otp)

                    //and sets the focus on previous digit
                }
                false
            })

        }
        binding.confirm.setOnClickListener{
            setOtp(it)
        }

    }
    fun getOtp():String{
        return otp[0].text.toString()+otp[1].text.toString()+otp[2].text.toString()+otp[3].text.toString()+otp[4].text.toString()+otp[5].text.toString()
    }

    fun setOtp(view: View) {
        binding.confirm.setBackgroundResource(R.drawable.background_header_transaction)
        var white= R.color.white
        binding.confirm.setTextColor(resources.getColor(white))
        var email = pref.getString(KEY_SIGNUP_EMAIL,"")
        viewModel.activateOTP(email!!,getOtp()).observe(viewLifecycleOwner, Observer {
            when(it.state){
                State.ERROR->{
                    loadingDialog.stop()
                }
                State.SUCCESS->{
                    if(it.data?.status!! != HttpsURLConnection.HTTP_OK){
                        loadingDialog.start("OTP Not Succesfully created")
                        loadingDialog.stop()

                    }else{
                        loadingDialog.start("OTP Succesfully created")
                        loadingDialog.stop()

                    }
                    Navigation.findNavController(view).navigate(R.id.action_otpFragment_to_loginFragment)
                    loadingDialog.stop()
                }
                State.LOADING->{
                    loadingDialog.start("Your process being loaded")

                }
            }


        })
    }

    }







