package com.jrektabasa.androidmvvm.data.api

import com.jrektabasa.androidmvvm.util.constant.StringConstant
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(StringConstant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}