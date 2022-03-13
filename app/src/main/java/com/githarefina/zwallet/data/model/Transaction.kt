package com.githarefina.zwallet.data.api.model

import android.graphics.drawable.Drawable

data class Transaction( var transactionImage:Drawable,
                        var transactionName :String,
                        var transactionNominal :Double,
                        var transactionType:String)