package com.jrektabasa.androidmvvm.api

import com.jrektabasa.androidmvvm.model.Album
import com.jrektabasa.androidmvvm.model.Comment
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.model.Todo
import com.jrektabasa.androidmvvm.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogInterface {

    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = 10,
    ): List<Post>

    @GET("posts/{id}")
    suspend fun getPostDetails(@Path("id") postId: Int): Post

    @GET("users/{id}")
    suspend fun getUserDetails(@Path("id") userId: Int): User

    @GET("comments")
    suspend fun getPostComments(@Query("postId") postId: Int): List<Comment>

    @GET("todos")
    suspend fun getTodos(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = 10,
    ): List<Todo>

    @GET("albums")
    suspend fun getAlbums(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = 10,
    ): List<Album>
}