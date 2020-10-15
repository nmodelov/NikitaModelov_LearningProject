package com.apps65.netutils

import android.content.Context
import com.apps65.netutils.connection.ConnectionService
import com.apps65.netutils.connection.FlowConnectionService
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetUtils {
    fun provideOkHttp(apply: ((OkHttpClient.Builder) -> Unit) = {}): OkHttpClient {
        return OkHttpClient.Builder()
            .apply(apply)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.BASIC
                    }
                }
            )
            .build()
    }

    fun provideConnectionService(
        context: Context,
        dispatcher: CoroutineDispatcher
    ): ConnectionService = FlowConnectionService(context, dispatcher)
}
