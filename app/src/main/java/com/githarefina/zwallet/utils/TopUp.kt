package com.githarefina.zwallet.utils

import android.widget.TextView
import com.githarefina.zwallet.data.model.UserDetail
import java.text.DecimalFormat

object TopUp {


    fun getData():List<String>{
        var listData= mutableListOf<String>()
        listData.add(0,"Go to the nearest ATM or you can \n" +
                "use E-Banking.")
        listData.add(1,"Type your security number on the\n" +
                "ATM or E-Banking.")
        listData.add(2,"Select “Transfer” in the menu")
        listData.add(3,"Type the virtual account number that\n" +
                "we provide you at the top.")
        listData.add(4,"Type the amount of the money you\n" +
                "want to top up.")
        listData.add(5,"Read the summary details")
        listData.add(6,"Press transfer / top up")
        listData.add(7,"You can see your money in Zwallet\n" +
                "within 3 hours.")
        return listData
    }



}