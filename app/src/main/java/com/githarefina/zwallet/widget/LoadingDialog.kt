package com.githarefina.zwallet.widget

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ProgressBar
import com.githarefina.zwallet.databinding.LoadingDialogBinding

class LoadingDialog(activity:Activity) {
    private val alertDialog:AlertDialog
    private val binding:LoadingDialogBinding

    init{
        val builder = AlertDialog.Builder(activity)
        binding = LoadingDialogBinding.inflate(activity.layoutInflater)
        alertDialog=builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }
    fun start(title:String){
        binding.desc.text=title
        alertDialog.show()
    }
    fun stop(){
        alertDialog.cancel()
    }
}