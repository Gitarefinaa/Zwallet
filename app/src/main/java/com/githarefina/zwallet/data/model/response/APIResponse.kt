package com.githarefina.zwallet.data.model.response

data class APIResponse<T>(var status:Int,var message: String,var data:T?)