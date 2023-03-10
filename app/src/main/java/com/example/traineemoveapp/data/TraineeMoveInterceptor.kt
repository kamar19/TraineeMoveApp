package com.example.traineemoveapp.data

import com.example.traineemoveapp.MainActivity.Companion.apiKey
import okhttp3.Interceptor
import okhttp3.Response

class TraineeMoveInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val request = originalHttpUrl
            .newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val url = originalRequest
            .newBuilder()
            .url(request)
            .build()
        return chain.proceed(url)
    }
}