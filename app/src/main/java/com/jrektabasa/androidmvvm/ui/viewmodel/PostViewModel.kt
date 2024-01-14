package com.jrektabasa.androidmvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.data.BlogInterface
import com.jrektabasa.androidmvvm.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val client: BlogInterface) : ViewModel() {
    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    suspend fun getPosts() {
        val posts: List<Post> = client.getPosts()
        Log.d("tara ai", "getPosts: $posts")
    }
}