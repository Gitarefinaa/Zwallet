package com.githarefina.zwallet.data.api

import com.githarefina.zwallet.data.api.model.UserModel
import com.githarefina.zwallet.data.model.*
import com.githarefina.zwallet.data.model.request.LoginRequest
import com.githarefina.zwallet.data.model.request.RefreshTokenRequest

import com.githarefina.zwallet.data.model.request.SignUpRequest
import com.githarefina.zwallet.data.model.request.TransferRequest
import com.githarefina.zwallet.data.model.response.APIResponse
import com.githarefina.zwallet.data.model.response.APIResponseTransfer
import com.githarefina.zwallet.data.model.response.ApIResponses
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ZwalletAPI {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): APIResponse<UserModel>

    @POST("auth/signup")
    suspend fun signup(@Body request: SignUpRequest):APIResponse<String>

    @GET("user/myProfile")
    suspend  fun getProfile():APIResponse<UserDetail>

    @GET("home/getInvoice")
     suspend fun getInvoice():APIResponse<List<Invoice>>

    @GET("auth/activate/{email}/{otp}")
    suspend fun activateOtp(@Path("email") email:String,@Path("otp")otp:String):APIResponse<String>


    @GET("auth/checkPIN/{pin}")
    suspend fun pinActivation(@Path("pin") pin:Int):APIResponse<String>

    @PATCH("auth/PIN")
    suspend fun checkPin(@Body json:JsonObject):APIResponse<String>



    @GET("tranfer/contactUser")
    suspend fun getContact():APIResponse<ArrayList<ContactModel>>

    @POST("tranfer/newTranfer")
    suspend fun transfer(@Body Transfer:TransferRequest,@Header("x-access-PIN") pin:String):APIResponseTransfer<TransferResponseModel>

    @GET("home/getBalance")
    suspend fun getBalance():APIResponse<List<Balance>>

    @POST("auth/refresh-token")
      fun refreshToken(@Body request: RefreshTokenRequest):Call<APIResponse<UserModel>>
}
