package com.githarefina.zwallet.network

import android.content.Context
import android.content.SharedPreferences
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.model.request.RefreshTokenRequest
import com.githarefina.zwallet.utils.KEY_USER_EMAIL
import com.githarefina.zwallet.utils.KEY_USER_TOKEN
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.net.ssl.HttpsURLConnection

class RefreshTokenInterceptor(
    private val client:ZwalletAPI,
    private val prefs :SharedPreferences
) :  Authenticator{
    override fun authenticate(route: Route?, response: Response): Request? {
    val updateToken = getNewToken()
    return  if(updateToken==null)
        null
     else
         response.request.newBuilder()
             .header("Authorization","Bearer $updateToken" )
             .build()
    }
    private fun getNewToken() :String?{
        val request = RefreshTokenRequest(
            prefs.getString(KEY_USER_EMAIL, "")!!, prefs.getString(
                KEY_USER_TOKEN, ""
            )!!
        )

        val response = client.refreshToken(request = request).execute().body()
        return if (response?.status !=HttpsURLConnection.HTTP_CREATED)
            null
        else{
            with(prefs.edit()){
                putString(KEY_USER_TOKEN,response.data?.token)
                apply()
            }
            response.data?.token
        }
    }
}