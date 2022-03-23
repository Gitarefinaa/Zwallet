package com.githarefina.zwallet.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.model.UserDetail
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.Resource
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(private var dataSource: ZwalletDataSource): ViewModel() {
    fun getProfile(): LiveData<Resource<APIResponse<UserDetail>?>> {
        return dataSource.getProfile()
    }
    fun changePassword(pin:JsonObject): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.changePassword(pin)
    }
    fun changeInfo(phone:JsonObject): LiveData<Resource<APIResponse<UserDetail>?>> {
        return dataSource.changePhone(phone)
    }
}