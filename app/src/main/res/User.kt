
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class User(
    @SerializedName("email")
    @Expose
    val email: String?,
    @SerializedName("expired_at")
    @Expose
    val expiredAt: Long?,
    @SerializedName("expired_in")
    @Expose
    val expiredIn: Int?,
    @SerializedName("hasPin")
    @Expose
    val hasPin: Boolean?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("refreshToken")
    @Expose
    val refreshToken: String?,
    @SerializedName("refreshToken_expired_at")
    @Expose
    val refreshTokenExpiredAt: Long?,
    @SerializedName("refreshToken_expired_in")
    @Expose
    val refreshTokenExpiredIn: Int?,
    @SerializedName("token")
    @Expose
    val token: String?
)