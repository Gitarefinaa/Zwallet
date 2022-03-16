
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class TRans(
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("notes")
    @Expose
    val notes: String?,
    @SerializedName("receiver")
    @Expose
    val `receiver`: Int?,
    @SerializedName("sender")
    @Expose
    val sender: Int?,
    @SerializedName("type")
    @Expose
    val type: Int?
)