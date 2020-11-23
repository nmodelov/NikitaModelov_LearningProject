package com.apps65.mvitemplate.data

import android.content.Context
import android.content.SharedPreferences
import com.apps65.mvi.common.DispatchersProvider
import com.apps65.mvi.common.DispatchersProviderImpl
import com.apps65.netutils.NetUtils
import com.apps65.netutils.connection.ConnectionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDIModule {

    @Provides
    @Singleton
    internal fun provideDispatchers(): DispatchersProvider = DispatchersProviderImpl

    @Provides
    @Singleton
    internal fun provideOkHttpClient() = NetUtils.provideOkHttp()

    @Provides
    @Singleton
    internal fun providePref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("pref_file", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideConnectionService(
        @ApplicationContext context: Context,
        dispatchersProvider: DispatchersProvider
    ): ConnectionService = NetUtils.provideConnectionService(context, dispatchersProvider.default)
}
