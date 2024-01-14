package com.jrektabasa.androidmvvm.di.module

import com.jrektabasa.androidmvvm.data.BlogInterface
import com.jrektabasa.androidmvvm.util.constant.StringConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = StringConstant.BASE_URL

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesRetrofitInstance(
        retrofit: Retrofit
    ): BlogInterface =
        retrofit.create(BlogInterface::class.java)

}