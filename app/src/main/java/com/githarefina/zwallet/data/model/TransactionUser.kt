package com.githarefina.zwallet.data.api.model
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class TransactionUser(
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("notes")
    @Expose
    val notes: String?,
    @SerializedName("receiver")
    @Expose
    val `receiver`: String?,
    @SerializedName("receiver_id")
    @Expose
    val receiverId: Int?,
    @SerializedName("receiver_image")
    @Expose
    val receiverImage: String?,
    @SerializedName("receiver_phone")
    @Expose
    val receiverPhone: String?,
    @SerializedName("sender")
    @Expose
    val sender: String?,
    @SerializedName("sender_id")
    @Expose
    val senderId: Int?,
    @SerializedName("sender_image")
    @Expose
    val senderImage: String?,
    @SerializedName("sender_phone")
    @Expose
    val senderPhone: String?,
    @SerializedName("type")
    @Expose
    val type: String?
)