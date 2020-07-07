package com.apps65.netutils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetUtils {
    fun provideOkHttp(apply: ((OkHttpClient.Builder) -> Unit) = {}): OkHttpClient {
        return OkHttpClient.Builder()
            .apply(apply)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()
    }
}
