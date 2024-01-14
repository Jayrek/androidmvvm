package com.jrektabasa.androidmvvm.repository.impl

import com.jrektabasa.androidmvvm.api.BlogInterface
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.repository.BlogRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRepositoryImpl @Inject constructor(
    private val blogInterface: BlogInterface
) : BlogRepository {
    override suspend fun getPosts(): List<Post> {
        return blogInterface.getPosts()
    }
}