package com.jrektabasa.androidmvvm.view.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.ActivityMainBinding
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.util.constant.IntentKeys.EXTRA_POST_ID
import com.jrektabasa.androidmvvm.view.adapter.BlogPostAdapter
import com.jrektabasa.androidmvvm.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var blogPostAdapter: BlogPostAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel: PostsViewModel by viewModels()
    private val blogPosts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
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

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = linearLayoutManager.childCount
                val totalItemCount: Int = linearLayoutManager.itemCount
                val firstVisibleItemPosition: Int =
                    linearLayoutManager.findFirstVisibleItemPosition()

                if (viewModel.isLoading.value == false
                    && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.getPosts()
                }
            }
        })
    }
}