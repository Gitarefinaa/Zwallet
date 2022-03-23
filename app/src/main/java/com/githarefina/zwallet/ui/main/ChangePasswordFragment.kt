package com.githarefina.zwallet.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.databinding.FragmentChangePasswordBinding
import com.githarefina.zwallet.utils.State
import com.githarefina.zwallet.viewmodel.ProfilViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import com.google.gson.JsonObject
import javax.net.ssl.HttpsURLConnection

class ChangePasswordFragment : Fragment() {
    private  lateinit var binding: FragmentChangePasswordBinding
    private lateinit  var loadingDialog: LoadingDialog
    private  val viewModel: ProfilViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentChangePasswordBinding.inflate(inflater,container,false)
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changePassword()

    }


    fun changePassword(){
        binding.confirm.setOnClickListener {view->
            if(!binding.newPassword.text.toString().equals(binding.repeatPassword.text.toString())){
                binding.repeatPassword.error = "Password harus sama"
            }else{
                var pin = JsonObject()
                pin.addProperty("old_password",binding.password.text.toString())
                pin.addProperty("new_password",binding.newPassword.text.toString())
                viewModel.changePassword(pin).observe(viewLifecycleOwner, Observer {
                    when(it.state){
                        State.LOADING->{
                            loadingDialog.start("Processing your request")
                        }
                        State.SUCCESS-> {
                            if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                                loadingDialog.start("Berhasil merubah password")
                                Navigation.findNavController(view).popBackStack()
                            }
                            else{
                               loadingDialog.start(it.data!!.message)
                            }
                            loadingDialog.stop()

                        }
                        State.ERROR->{
                            loadingDialog.stop()

                        }
                    }
                })
            }

        }


    }

}