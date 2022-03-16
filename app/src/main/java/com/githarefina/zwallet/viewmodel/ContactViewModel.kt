package com.githarefina.zwallet.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.model.Balance
import com.githarefina.zwallet.data.model.ContactModel
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private var dataSource: ZwalletDataSource):ViewModel() {

    fun getContact(): LiveData<Resource<APIResponse<ArrayList<ContactModel>>?>> {
        return dataSource.getContact()
    }
}