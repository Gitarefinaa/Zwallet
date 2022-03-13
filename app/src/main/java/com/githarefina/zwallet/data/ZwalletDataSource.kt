package com.githarefina.zwallet.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.api.model.UserModel
import com.githarefina.zwallet.data.model.Balance
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.data.model.UserDetail
import com.githarefina.zwallet.data.model.request.LoginRequest
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.data.model.response.ApIResponses
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class ZwalletDataSource(private val apiClient: ZwalletAPI) {
        fun login(email:String,Password:String)= liveData<APIResponse<UserModel>>(Dispatchers.IO){
            try {
                val loginRequest =LoginRequest(email=email, password = Password)
                val response =apiClient.login(loginRequest)
                emit(response)
            }catch (e :Exception){
                emit(APIResponse(400,e.localizedMessage,null))
            }
        }

    fun getProfile() = liveData<APIResponse<UserDetail>> {
        try{
            val response = apiClient.getProfile()
            emit(response)
        }catch (e :Exception){
            emit(APIResponse(400,e.localizedMessage,null))
        }



    }
    fun getInvoice()= liveData<APIResponse<List<Invoice>>>(Dispatchers.IO){
        try {
            val response =apiClient.getInvoice()
            emit(response)
        }catch (e :Exception){
            emit(APIResponse(400,e.localizedMessage,null))
        }
    }

    fun getBalance()= liveData<APIResponse<List<Balance>>>(Dispatchers.IO){
        try {
            val response =apiClient.getBalance()
            emit(response)
        }catch (e :Exception){
            emit(APIResponse(400,e.localizedMessage,null))
        }
    }

    fun setOTP(email:String,password:String)= liveData<APIResponse<String>>(Dispatchers.IO){
        try {
            val response =apiClient.activateOtp(email,password)
            emit(response)
        }catch (e :Exception){
            emit(APIResponse(400,e.localizedMessage,""))
        }
    }



}