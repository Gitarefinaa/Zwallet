package com.githarefina.zwallet.di

import android.content.ContentProviderClient
import android.content.Context
import com.githarefina.zwallet.data.ZwalletDataSource
import com.githarefina.zwallet.data.api.ZwalletAPI
import com.githarefina.zwallet.network.NetworkConfig
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
    @Singleton
    fun provideApi(@ApplicationContext context: Context):ZwalletAPI = NetworkConfig(context =context ).buildAPI()




    @Provides
    @Singleton
    fun provideDataSource(apiClient: ZwalletAPI):ZwalletDataSource = ZwalletDataSource(apiClient)


}