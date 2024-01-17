package com.jrektabasa.androidmvvm.repository

import com.jrektabasa.androidmvvm.model.Comment
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.model.User

interface BlogRepository {

    suspend fun getPosts(page: Int): List<Post>
    suspend fun getPostDetails(postId: Int): Post
    suspend fun getUserDetails(userId: Int): User
    suspend fun getPostComments(postId: Int): List<Comment>
}