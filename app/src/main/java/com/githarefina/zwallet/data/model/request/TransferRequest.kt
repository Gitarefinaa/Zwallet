package com.githarefina.zwallet.data.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransferRequest (
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("notes")
    @Expose
    val notes: String?,
    @SerializedName("receiver")
    @Expose
    val receiver: String?
)
