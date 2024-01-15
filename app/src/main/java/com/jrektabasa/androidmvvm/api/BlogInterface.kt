package com.jrektabasa.androidmvvm.api

import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogInterface {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostDetails(@Path("id") postId: Int): Post

    @GET("users/{id}")
    suspend fun getUserDetails(@Path("id") userId: Int): User
}