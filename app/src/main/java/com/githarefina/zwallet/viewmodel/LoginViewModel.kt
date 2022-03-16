package com.githarefina.zwallet.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.api.model.UserModel
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var dataSource: ZwalletDataSource):ViewModel() {



    fun login(email: String, password: String): LiveData<Resource<APIResponse<UserModel>?>> {
        return dataSource.login(email, password)
    }
    //        val loginRequest = LoginRequest(email, password)
//        val response = apiClient.login(loginRequest).execute()
//        if (response.isSuccessful) {
//            if (response.body()?.status == HttpsURLConnection.HTTP_OK) {
//
//            } else {
//                val res = response.body()!!.data
//                with(prefs.edit()) {
//                    putBoolean(KEY_LOGGED_IN, true)
//                    putString(KEY_USER_EMAIL, res?.email)
//                    putString(KEY_USER_TOKEN, res?.token)
//                    putString(KEY_USER_REFRESH_TOKEN, res?.refreshToken)
//                    apply()
//                }
//            }
//        }
//        return response.body()
//        apiClient.login(loginRequest).enqueue(object :
//            Callback<APIResponse<UserModel>> {
//            override fun onResponse(
//                call: Call<APIResponse<UserModel>>, response: Response<APIResponse<UserModel>>
//            ) {
//                if (response.body()?.status != HttpsURLConnection.HTTP_OK) {
//                    Toast.makeText(app, "Wrong email dan password ", Toast.LENGTH_LONG).show()
//
//                } else {
//                    val res = response.body()!!.data
//                    with(prefs.edit()) {
//                        putBoolean(KEY_LOGGED_IN, true)
//                        putString(KEY_USER_EMAIL, res.email)
//                        putString(KEY_USER_TOKEN, res.token)
//                        putString(KEY_USER_REFRESH_TOKEN, res.refreshToken)
//                        apply()
//                        Toast.makeText(app, "Login berhasil", Toast.LENGTH_LONG).show()
//
////                        toMain()
//                    }
//
//                }
//            }

//            override fun onFailure(call: Call<APIResponse<UserModel>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//    fun toMain(){
//        val intent = Intent(app, MainActivity::class.java)
//        startActivity(intent)
//        finish
//
//    }
}