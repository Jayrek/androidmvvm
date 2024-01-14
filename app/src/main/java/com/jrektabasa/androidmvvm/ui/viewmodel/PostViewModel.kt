package com.jrektabasa.androidmvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.data.BlogInterface
import com.jrektabasa.androidmvvm.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val client: BlogInterface) : ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> = _posts

    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    suspend fun getPosts() {
        try {
            val response = client.getPosts()
            _posts.value = response
        } catch (e: Exception) {
            _posts.value = emptyList()
        }

    }
}