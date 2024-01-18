package com.jrektabasa.androidmvvm.repository.impl

import com.jrektabasa.androidmvvm.api.BlogInterface
import com.jrektabasa.androidmvvm.model.Album
import com.jrektabasa.androidmvvm.model.Comment
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.model.Todo
import com.jrektabasa.androidmvvm.model.User
import com.jrektabasa.androidmvvm.repository.BlogRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRepositoryImpl @Inject constructor(
    private val blogInterface: BlogInterface
) : BlogRepository {
    override suspend fun getPosts(page: Int): List<Post> = blogInterface.getPosts(page)

    override suspend fun getPostDetails(
        postId: Int
    ): Post = blogInterface.getPostDetails(postId)

    override suspend fun getUserDetails(
        userId: Int
    ): User = blogInterface.getUserDetails(userId)

    override suspend fun getUsers(): List<User> = blogInterface.getUsers()

    override suspend fun getPostComments(postId: Int): List<Comment> =
        blogInterface.getPostComments(postId)

    override suspend fun getTodos(page: Int): List<Todo> = blogInterface.getTodos(page)

    override suspend fun getAlbums(page: Int): List<Album> = blogInterface.getAlbums(page)

}