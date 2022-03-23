package com.githarefina.zwallet.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor(val tokenProvider: ()->String) :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.invoke()

        val newRequest = chain.request().newBuilder()
        if(token.isNullOrEmpty())    {
            newRequest.header("Authorization", "Bearer $token")
        }
        return chain.proceed(newRequest.build())
    }
}