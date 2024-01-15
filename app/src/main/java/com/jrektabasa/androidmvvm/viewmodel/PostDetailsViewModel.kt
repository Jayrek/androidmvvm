package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.model.User
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _postDetail: MutableLiveData<Post> = MutableLiveData()
    val postDetail: LiveData<Post> = _postDetail

    private val _userDetail: MutableLiveData<User> = MutableLiveData()
    val userDetails: LiveData<User> = _userDetail

    suspend fun getPostDetail(postId: Int) {
        try {
            val post = blogRepository.getPostDetails(postId)
            val user = blogRepository.getUserDetails(post.userId)
            _postDetail.value = post
            _userDetail.value = user
            Log.d("post", "getPostDetail: $post")
            Log.d("user", "_userDetail: $user")
        } catch (e: Exception) {

        }
    }
}