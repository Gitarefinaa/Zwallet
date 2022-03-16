package com.githarefina.zwallet.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class Invoice(
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("notes")
    @Expose
    val notes: String?,
    @SerializedName("phone")
    @Expose
    val phone: String?,
    @SerializedName("receiver")
    @Expose
    val receiver: Int?,
    @SerializedName("sender")
    @Expose
    val sender: Int?,
    @SerializedName("type")
    @Expose
    val type: String?
)