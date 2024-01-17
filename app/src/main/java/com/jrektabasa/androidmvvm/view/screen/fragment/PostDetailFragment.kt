package com.jrektabasa.androidmvvm.view.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jrektabasa.androidmvvm.R
import com.jrektabasa.androidmvvm.databinding.FragmentPostDetailBinding
import com.jrektabasa.androidmvvm.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel: PostDetailsViewModel by viewModels()

    private val args: PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(requireActivity()) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.postDetail.observe(requireActivity()) { post ->
            binding.textviewPostId.text = requireActivity().getString(R.string.post, post?.id)
            binding.textviewPostTitle.text = post?.title
            binding.textviewPostBody.text = post?.body
        }

        viewModel.userDetails.observe(requireActivity()) { user ->
            user.let {

                binding.textviewLabelName.text = getString(R.string.label_name)
                binding.textviewLabelUserName.text = getString(R.string.label_user_name)
                binding.textviewLabelEmail.text = getString(R.string.label_email)
                binding.textviewLabelWebsite.text = getString(R.string.label_website)

                binding.textviewName.text = user?.name
                binding.textviewUserName.text = user?.username
                binding.textviewUserEmail.text = user?.email
                binding.textviewWebview.text = user?.website
            }
        }

        viewModel.getPostDetail(args.postId)
    }

}