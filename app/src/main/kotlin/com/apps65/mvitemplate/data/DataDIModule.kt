package com.apps65.mvitemplate.data

import android.content.Context
import android.content.SharedPreferences
import com.apps65.mvitemplate.common.DispatchersProvider
import com.apps65.netutils.NetUtils
import com.apps65.netutils.connection.ConnectionService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataDIModule.Declarations::class])
object DataDIModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient() = NetUtils.provideOkHttp()

    @Provides
    @Singleton
    internal fun providePref(context: Context): SharedPreferences =
        context.getSharedPreferences("pref_file", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideConnectionService(
        context: Context,
        dispatchersProvider: DispatchersProvider
    ): ConnectionService = NetUtils.provideConnectionService(context, dispatchersProvider.default)

    @Module
    internal interface Declarations {
        // TBD
    }
}
