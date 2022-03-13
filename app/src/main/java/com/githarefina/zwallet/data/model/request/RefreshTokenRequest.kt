package com.githarefina.zwallet.data.model.request

data class RefreshTokenRequest (
    var email:String,
    var refreshToken :String
        )