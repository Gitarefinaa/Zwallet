package com.githarefina.zwallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.model.TransactionUser
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private var dataSource: ZwalletDataSource): ViewModel() {
    fun getAllInvoice(): LiveData<Resource<APIResponse<List<Invoice>>?>> {
        return dataSource.getAllInvoice()
    }
    fun getDataTransaction(id:Int): LiveData<Resource<APIResponse<TransactionUser>?>> {
        return dataSource.getUserTransaction(id)
    }
}