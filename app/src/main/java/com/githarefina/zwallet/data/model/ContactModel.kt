package com.githarefina.zwallet.data.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class ContactModel(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("phone")
    @Expose
    val phone: String?
)