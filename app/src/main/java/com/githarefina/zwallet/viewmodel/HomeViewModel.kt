package com.githarefina.zwallet.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.model.Balance
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.data.model.UserDetail
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.network.NetworkConfig

class HomeViewModel(app:Application): ViewModel() {
    private val invoices= MutableLiveData<APIResponse<List<Invoice>>>()
    private val balance= MutableLiveData<APIResponse<List<Balance>>>()

    private var apiClient: ZwalletAPI = NetworkConfig(app).buildAPI()

    private var dataSource =ZwalletDataSource(apiClient)
    fun getInvoice(): LiveData<APIResponse<List<Invoice>>> {
        return dataSource.getInvoice()
    }
    fun getBalance():LiveData<APIResponse<List<Balance>>>{
        return dataSource.getBalance()

    }
}