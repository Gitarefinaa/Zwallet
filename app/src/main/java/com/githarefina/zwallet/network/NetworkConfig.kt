package com.githarefina.zwallet.network

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.utils.BASE_URL
import com.githarefina.zwallet.utils.KEY_USER_TOKEN
import com.githarefina.zwallet.utils.PREFS_NAME
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkConfig(val context: Context) {
    val pref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun getInterceptor(authenticator: Authenticator?=null):OkHttpClient{
        val token =pref.getString(KEY_USER_TOKEN,"")
        val interceptor : TokenInterceptor
        val logging = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()
        logging.level = HttpLoggingInterceptor.Level.BODY
        if(!token.isNullOrEmpty()) {
            interceptor = TokenInterceptor(token= token!!)
            return client.addInterceptor(logging).addInterceptor(interceptor).build()
        }
        if(authenticator !=null){
           return client.addInterceptor(TokenInterceptor(token=token!!)).build()
        }
        else{
            return client.addInterceptor(logging).build()
        }
    }
    private fun getService(): ZwalletAPI {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())

            .build().create(ZwalletAPI::class.java)
    }

//    fun getRetrofit():ZwalletAPI{
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .client(getInterceptor())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(ZwalletAPI::class.java)
//    }

    fun buildAPI():ZwalletAPI{
        val authenticator= RefreshTokenInterceptor(context, getService(),pref )
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(getInterceptor(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ZwalletAPI::class.java)
    }

}