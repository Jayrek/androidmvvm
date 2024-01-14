package com.jrektabasa.androidmvvm.repository

import com.jrektabasa.androidmvvm.model.Post

interface BlogRepository {

    suspend fun getPosts(): List<Post>
}