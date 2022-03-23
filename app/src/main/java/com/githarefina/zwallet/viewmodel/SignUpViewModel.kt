package com.githarefina.zwallet.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class SignUpViewModel @Inject constructor(private var dataSource: ZwalletDataSource):ViewModel() {
    private lateinit var data_email:MutableLiveData<String>

    fun signUp(username: String,email: String, password: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.signUp(username,email, password)
    }


}