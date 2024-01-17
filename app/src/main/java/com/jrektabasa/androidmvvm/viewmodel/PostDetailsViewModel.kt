package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.Comment
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.model.User
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _postDetail: MutableLiveData<Post?> = MutableLiveData()
    val postDetail: LiveData<Post?> = _postDetail

    private val _comments: MutableLiveData<List<Comment>> = MutableLiveData(emptyList())
    val comments: LiveData<List<Comment>> = _comments

    private val _userDetail: MutableLiveData<User?> = MutableLiveData()
    val userDetails: LiveData<User?> = _userDetail

    fun getPostDetail(postId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val post = blogRepository.getPostDetails(postId)
                val user = blogRepository.getUserDetails(post.userId)
                val comments = blogRepository.getPostComments(post.id)
                _postDetail.value = post
                _userDetail.value = user
                _comments.value = comments
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _postDetail.value = null
                _userDetail.value = null
            }
        }
    }
}