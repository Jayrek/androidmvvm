package com.jrektabasa.androidmvvm.view.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jrektabasa.androidmvvm.databinding.ActivityMainBinding
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.util.constant.IntentKeys.EXTRA_POST_ID
import com.jrektabasa.androidmvvm.view.adapter.BlogPostAdapter
import com.jrektabasa.androidmvvm.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var blogPostAdapter: BlogPostAdapter

    private val viewModel: PostsViewModel by viewModels()
    private val blogPosts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayPosts()
        viewModel.getPosts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayPosts() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.posts.observe(this) { posts ->
            blogPosts.clear()
            blogPosts.addAll(posts)
            blogPostAdapter.notifyDataSetChanged()
        }
        blogPostAdapter =
            BlogPostAdapter(this, blogPosts, object : BlogPostAdapter.OnItemClickListener {
                override fun onClick(post: Post) {
                    val intent = Intent(this@MainActivity, PostDetailsActivity::class.java)
                    intent.putExtra(EXTRA_POST_ID, post.id)
                    startActivity(intent)
                }
            })

        binding.recyclerView.adapter = blogPostAdapter
    }
}