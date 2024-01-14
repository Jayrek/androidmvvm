package com.jrektabasa.androidmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.api.BlogInterface
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val blogRepository: BlogRepository) : ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> = _posts

    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    suspend fun getPosts() {
        try {
            val response = blogRepository.getPosts()
            _posts.value = response
        } catch (e: Exception) {
            _posts.value = emptyList()
        }

    }
}