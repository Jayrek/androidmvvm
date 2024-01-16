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

    private var currentPage = 1

    fun getPosts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val postResponse = blogRepository.getPosts(
                    page = currentPage,
                )
                currentPage += 1
                val blogPosts = _posts.value ?: emptyList()
                _posts.value = blogPosts + postResponse
            } catch (e: Exception) {
                _posts.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}