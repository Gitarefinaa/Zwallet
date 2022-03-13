package com.githarefina.zwallet.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

data class UserDetail(
    @SerializedName("email")
    @Expose
    val email: String?,
    @SerializedName("firstname")
    @Expose
    val firstname: String?,
    @SerializedName("lastname")
    @Expose
    val lastname:  String?,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("image")
    @Expose
    val balance: String?,

    @SerializedName("phone")
    @Expose
    val phone: String?
)