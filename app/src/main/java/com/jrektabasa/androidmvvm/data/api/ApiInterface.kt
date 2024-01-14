package com.jrektabasa.androidmvvm.data.api

import com.jrektabasa.androidmvvm.model.Post
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}