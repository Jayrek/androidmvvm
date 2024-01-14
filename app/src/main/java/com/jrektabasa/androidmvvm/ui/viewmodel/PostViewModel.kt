package com.jrektabasa.androidmvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.data.api.ApiClient
import com.jrektabasa.androidmvvm.model.Post
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    private suspend fun getPosts() {
        val posts: List<Post> = ApiClient.api.getPosts()
        Log.d("tara ai", "getPosts: $posts")
    }
}