
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class TransferRequest(
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("notes")
    @Expose
    val notes: String?,
    @SerializedName("receiver")
    @Expose
    val `receiver`: String?
)