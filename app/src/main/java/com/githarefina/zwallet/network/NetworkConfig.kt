package com.githarefina.zwallet.network

import android.content.Context
import android.content.SharedPreferences
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.utils.*
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkConfig @Inject constructor(var pref: SharedPreferences) {
    fun getInterceptor(authenticator: Authenticator?=null):OkHttpClient{
        var token =pref.getString(KEY_USER_TOKEN,"")
        Log.d("tokenCoba", token!!)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logging)
        if(!token.isNullOrEmpty()) {
             client.addInterceptor(TokenInterceptor{ token })
        }
        if(authenticator !=null){
             client.authenticator(authenticator)
        }
        return client.build()

    }
    private fun getService(): ZwalletAPI {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())

            .build()
            .create(ZwalletAPI::class.java)
    }

//    fun getRetrofit():ZwalletAPI{
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .client(getInterceptor())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(ZwalletAPI::class.java)
//    }

    fun buildAPI():ZwalletAPI{
        val authenticator= RefreshTokenInterceptor( getService(),pref )
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(getInterceptor(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ZwalletAPI::class.java)
    }

}