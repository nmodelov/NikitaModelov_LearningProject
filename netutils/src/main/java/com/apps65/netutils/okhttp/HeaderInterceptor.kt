package com.apps65.netutils.okhttp

import okhttp3.Interceptor
import okhttp3.Response

open class HeaderInterceptor(
    private val headerName: String,
    private val valueProvider: () -> String?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().apply {
            val value = valueProvider.invoke()
            if (value != null && value.isNotBlank()) {
                addHeader(headerName, value)
            }
        }
        return chain.proceed(builder.build())
    }
}
