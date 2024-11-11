package com.example.colabtasks_app.Api

import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        println("Sending request: ${request.url()} on ${chain.connection()} ${request.headers()}")

        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        println("Received response for ${response.request().url()} in ${(t2 - t1) / 1e6}ms ${response.headers()}")

        return response
    }
}