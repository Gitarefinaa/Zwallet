package com.githarefina.zwallet.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.data.model.response.ApIResponses
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.Resource

class PinViewModel(app:Application):ViewModel() {
    private var apiClient: ZwalletAPI = NetworkConfig(app).buildAPI()
    private var dataSource = ZwalletDataSource(apiClient)


    fun pinActivation(pin:String): LiveData<Resource<APIResponse<String>?>> {
        return  dataSource.setPin(pin)

    }


}