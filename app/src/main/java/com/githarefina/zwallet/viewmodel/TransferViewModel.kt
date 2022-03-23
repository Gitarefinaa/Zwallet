package com.githarefina.zwallet.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.model.Balance
import com.githarefina.zwallet.data.model.ContactModel
import com.githarefina.zwallet.data.model.TransferResponseModel
import com.githarefina.zwallet.data.model.request.TransferRequest
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.data.model.response.APIResponseTransfer
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(private var dataSource: ZwalletDataSource):ViewModel() {
    var data_amount  = MutableLiveData<Int>()
    var data_receiver = MutableLiveData<Int>()
    var data_notes = MutableLiveData<String>()
    var data_pin = MutableLiveData<String>()
    var data_balance = MutableLiveData<Int>()
    var name = MutableLiveData<String>()
    var data_contact = MutableLiveData<ContactModel>()
    var data_transfer = MutableLiveData<TransferResponseModel>()

    fun setAmount(amount:Int){
        data_amount.postValue(amount)
    }
    fun receiver(id: Int?){
        data_receiver.postValue(id!!)
    }
    fun notes(notes:String){
        data_notes.postValue(notes)
    }
    fun setPin(pin:String){
        data_pin.postValue(pin)
    }

    fun setContact(contact: ContactModel) {
        data_contact.postValue(contact)

    }

    fun setTransfer(data: TransferResponseModel?) {
        data_transfer.postValue(data!!)


    }
    fun getContactName(name:String): LiveData<Resource<APIResponse<ArrayList<ContactModel>>?>> {
        return dataSource.getUserbyName(name)
    }



    fun transfer(trf:TransferRequest,pin:String): LiveData<Resource<APIResponseTransfer<TransferResponseModel>?>> {
        return  dataSource.Transfer(trf,pin)
    }
    fun balance(): LiveData<Resource<APIResponse<List<Balance>>?>> {
        return dataSource.getBalance()
    }

    fun getContact(): LiveData<Resource<APIResponse<ArrayList<ContactModel>>?>> {
        return dataSource.getContact()
    }

    fun setName(it: String) {
        return name.postValue(it)

    }


}