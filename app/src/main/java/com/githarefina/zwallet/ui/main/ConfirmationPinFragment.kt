package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.githarefina.zwallet.R
import com.githarefina.zwallet.data.model.request.TransferRequest
import com.githarefina.zwallet.databinding.FragmentConfirmationPinBinding
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.utils.State
import com.githarefina.zwallet.viewmodel.TransferViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmationPinFragment : Fragment() {
    private lateinit var binding:FragmentConfirmationPinBinding
    var pin  = mutableListOf<EditText>()
    private  lateinit var pref: SharedPreferences
    private lateinit  var loadingDialog: LoadingDialog
    private  val viewModel: TransferViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationPinBinding.inflate(inflater,container,false)
        loadingDialog= LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEditText()
        pref = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        binding.confirm.setOnClickListener {
            setpin(it)
            transfer(it)

        }
    }

    fun initEditText(){
        pin.add(0,binding.pin1)
        pin.add(1,binding.pin2)
        pin.add(2,binding.pin3)
        pin.add(3,binding.pin4)
        pin.add(4,binding.pin5)
        pin.add(5,binding.pin6)
        pinHandler()
    }
    fun pinHandler(){
        for (i in 0..5) { //Its designed for 6 digit pin
            pin.get(i).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    pin.get(i).setBackgroundResource(R.drawable.background_edit_text_otp_after)

                }
                override fun afterTextChanged(s: Editable) {
                    if (i == 5 && !pin.get(i).getText().toString().isEmpty()) {
                        pin.get(i).clearFocus()

                        //Clears focus when you have entered the last digit of the pin.
                    } else if (!pin.get(i).getText().toString().isEmpty()) {
                        pin.get(i + 1).requestFocus() //focuses on the next edittext after a digit is entered.
                    }
                }
            })
            pin.get(i).setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (event.action !== KeyEvent.ACTION_DOWN) {
                    return@OnKeyListener false //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                }
                if (keyCode == KeyEvent.KEYCODE_DEL && pin.get(i).getText().toString().isEmpty() && i != 0) {
                    //this condition is to handel the delete input by users.
                    pin.get(i - 1).setText("") //Deletes the digit of pin
                    pin.get(i - 1).requestFocus()
                    pin.get(i - 1).setBackgroundResource(R.drawable.background_edit_text_otp)

                    //and sets the focus on previous digit
                }
                false
            })

        }
    }
    fun getpin():String{
        return pin[0].text.toString()+pin[1].text.toString()+pin[2].text.toString()+pin[3].text.toString()+pin[4].text.toString()+pin[5].text.toString()
    }

    fun setpin(view: View) {
        binding.confirm.setBackgroundResource(R.drawable.background_header_transaction)
        var white= R.color.white
        binding.confirm.setTextColor(resources.getColor(white))
    }
    fun transfer(view: View) {
        var receiver = viewModel.data_receiver.value.toString()
        var amount =viewModel.data_amount.value
        var notes = viewModel.data_notes.value

        var balance = viewModel.balance().value?.data?.data?.get(0)?.balance
        viewModel.transfer(TransferRequest(amount=amount,notes=notes,receiver=receiver),getpin()).observe(viewLifecycleOwner, Observer {

            when(it.state){
                State.ERROR->{
                    loadingDialog.stop()

                }
                State.SUCCESS->{
                    viewModel.setTransfer(it.data?.details)
                    loadingDialog.start("Transfer Success")
                    loadingDialog.stop()

                    Navigation.findNavController(view).navigate(R.id.action_confirmationPinFragment_to_fragmentTransferSuccess)

                }
                State.LOADING->{
                    loadingDialog.start("your process being loaded")



                }
            }
        })
    }

}