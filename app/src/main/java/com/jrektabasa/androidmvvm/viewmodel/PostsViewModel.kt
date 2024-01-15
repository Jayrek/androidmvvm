package com.jrektabasa.androidmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val blogRepository: BlogRepository) : ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> = _posts

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    suspend fun getPosts() {
        try {
            _isLoading.value = true
            val response = blogRepository.getPosts()
            _posts.value = response
            _isLoading.value = false
        } catch (e: Exception) {
            _isLoading.value = false
            _posts.value = emptyList()
        }

    }
}