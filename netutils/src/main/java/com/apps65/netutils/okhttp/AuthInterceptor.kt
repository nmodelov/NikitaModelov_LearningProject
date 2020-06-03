package com.apps65.netutils.okhttp

class AuthInterceptor(private val tokenProvider: () -> String?) :
    HeaderInterceptor("Authorization", { "Bearer " + tokenProvider.invoke() })
