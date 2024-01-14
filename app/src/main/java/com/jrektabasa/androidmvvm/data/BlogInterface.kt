package com.jrektabasa.androidmvvm.data

import com.jrektabasa.androidmvvm.model.Post
import retrofit2.http.GET

interface BlogInterface {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}