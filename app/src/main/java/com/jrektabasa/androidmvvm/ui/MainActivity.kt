package com.jrektabasa.androidmvvm.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jrektabasa.androidmvvm.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.jrektabasa.androidmvvm.databinding.ActivityMainBinding
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.ui.adapter.BlogPostAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var blogPostAdapter: BlogPostAdapter

    private val viewModel: PostViewModel by viewModels()
    private val blogPosts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayPosts()
        getPosts()
    }

    private fun getPosts() {
        lifecycleScope.launch {
            viewModel.getPosts()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayPosts() {
        viewModel.posts.observe(this) { posts ->
            blogPosts.clear()
            blogPosts.addAll(posts)
            blogPostAdapter.notifyDataSetChanged()
        }
        blogPostAdapter = BlogPostAdapter(this, blogPosts)
        binding.recyclerView.adapter = blogPostAdapter
    }
}