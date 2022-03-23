package com.githarefina.zwallet.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.data.api.model.UserModel
import com.githarefina.zwallet.data.model.Balance
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.data.model.UserDetail
import com.githarefina.zwallet.data.model.request.LoginRequest
import com.githarefina.zwallet.data.model.request.SignUpRequest
import com.githarefina.zwallet.data.model.request.TransferRequest
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.data.model.response.ApIResponses
import com.githarefina.zwallet.utils.Resource
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

class ZwalletDataSource @Inject constructor(private val apiClient: ZwalletAPI) {
        fun login(email:String,Password:String)= liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            try {
                val loginRequest = LoginRequest(email = email, password = Password)
                val response = apiClient.login(loginRequest)
                emit(Resource.success(response))
            } catch (e: Exception) {
                emit(Resource.error(null, e.localizedMessage))
            }
        }

    fun getUserbyName(name:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try{
            val response = apiClient.getSearchUser(name)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }



    }

    fun getAllInvoice() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response =apiClient.getAllInvoice()
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }
    fun getProfile() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try{
            val response = apiClient.getProfile()
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }
    fun getInvoice()= liveData(Dispatchers.IO){
       emit(Resource.loading(null))
        try {
            val response =apiClient.getInvoice()
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }


    fun getBalance()= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.getBalance()
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }

    fun setOTP(email:String,password:String)= liveData(Dispatchers.IO){
       emit(Resource.loading(null))
        try {
            val response =apiClient.activateOtp(email,password)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }

    fun setPin(pin:JsonObject)= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.checkPin(pin)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }


    fun activatePin(pin:Int)= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.pinActivation(pin)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }


    fun signUp(username:String,email: String,password: String)= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val signUpRequest =SignUpRequest(email=email, password = password, username=username)
            val response =apiClient.signup(signUpRequest)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }

    }


    fun getContact()= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.getContact()
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }

    }
    fun Transfer(transfer:TransferRequest,pin:String)= liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.transfer(transfer,pin)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }

    fun changePassword(pin :JsonObject) =liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.changePin(pin)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }

    fun changePhone(phone :JsonObject) =liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response =apiClient.changePhoneNumber(phone)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }

    fun getUserTransaction(id:Int) =liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try{
            val response = apiClient.getTransactionName(id)
            emit(Resource.success(response))
        }catch (e :Exception){
            emit(Resource.error(null,e.localizedMessage))
        }
    }


}