package com.apps65.mvitemplate.data

import android.content.Context
import android.content.SharedPreferences
import com.apps65.netutils.NetUtils
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

    @Module
    internal interface Declarations {
        // TBD
    }
}
