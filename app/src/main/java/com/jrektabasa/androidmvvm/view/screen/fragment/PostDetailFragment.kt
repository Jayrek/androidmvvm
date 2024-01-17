package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jrektabasa.androidmvvm.R
import com.jrektabasa.androidmvvm.databinding.FragmentPostDetailBinding
import com.jrektabasa.androidmvvm.model.Comment
import com.jrektabasa.androidmvvm.view.adapter.PostCommentAdapter
import com.jrektabasa.androidmvvm.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var postCommentAdapter: PostCommentAdapter

    private val postComments = mutableListOf<Comment>()

    private val args: PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPostDetail(args.postId)

        viewModel.isLoading.observe(requireActivity()) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.postDetail.observe(requireActivity()) { post ->
            binding.textviewPostTitle.text = post?.title
            binding.textviewPostBody.text = post?.body
            binding.textviewLabelComments.text = getString(R.string.label_comments)
        }

        viewModel.userDetails.observe(requireActivity()) { user ->
            binding.textviewName.text = user?.name
        }

        postCommentAdapter = PostCommentAdapter(postComments)

        viewModel.comments.observe(requireActivity()) { comments ->
            postComments.clear()
            postComments.addAll(comments)
            postCommentAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = postCommentAdapter
    }

}