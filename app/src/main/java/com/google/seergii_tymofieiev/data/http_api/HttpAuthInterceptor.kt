package com.google.seergii_tymofieiev.data.http_api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class HttpAuthInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //Build new request
        val builder = request.newBuilder()
        //builder.header("Accept-Language", "uk")
        request = builder.build()
        return chain.proceed(request)
    }

}