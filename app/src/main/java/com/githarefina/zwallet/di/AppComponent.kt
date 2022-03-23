package com.githarefina.zwallet.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.network.NetworkConfig
import com.githarefina.zwallet.utils.PREFS_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context =context


   @Provides
   @Singleton
   fun getSharedPreferences(context: Context) = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideApi(pref: SharedPreferences):ZwalletAPI = NetworkConfig(pref).buildAPI()


    @Provides
    @Singleton
    fun provideDataSource(apiClient: ZwalletAPI):ZwalletDataSource = ZwalletDataSource(apiClient)


}