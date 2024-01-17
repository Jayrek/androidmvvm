package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.FragmentPostBinding
import com.jrektabasa.androidmvvm.model.Post
import com.jrektabasa.androidmvvm.view.adapter.BlogPostAdapter
import com.jrektabasa.androidmvvm.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var blogPostAdapter: BlogPostAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel: PostsViewModel by viewModels()
    private val blogPosts = mutableListOf<Post>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager
        displayPosts()
        viewModel.getPosts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayPosts() {
        viewModel.isLoading.observe(requireActivity()) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.posts.observe(requireActivity()) { posts ->
            blogPosts.clear()
            blogPosts.addAll(posts)
            blogPostAdapter.notifyDataSetChanged()
        }
        blogPostAdapter =
            BlogPostAdapter(
                requireContext(),
                blogPosts,
                object : BlogPostAdapter.OnItemClickListener {
                    override fun onClick(post: Post) {
                        val action =
                            PostFragmentDirections
                                .actionPostFragmentToPostDetailFragment()
                                .setPostId(post.id)
                        findNavController().navigate(action)
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