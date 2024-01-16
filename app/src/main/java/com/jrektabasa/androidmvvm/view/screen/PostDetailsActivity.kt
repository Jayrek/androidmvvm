package com.jrektabasa.androidmvvm.view.screen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jrektabasa.androidmvvm.R
import com.jrektabasa.androidmvvm.databinding.ActivityPostDetailBinding
import com.jrektabasa.androidmvvm.util.constant.IntentKeys.EXTRA_POST_ID
import com.jrektabasa.androidmvvm.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private val viewModel: PostDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getIntExtra(EXTRA_POST_ID, -1)

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.postDetail.observe(this) { post ->
            binding.textviewPostId.text = this.getString(R.string.post, post?.id)
            binding.textviewPostTitle.text = post?.title
            binding.textviewPostBody.text = post?.body
        }

        viewModel.userDetails.observe(this) { user ->
            user.let {

            binding.textviewLabelName.text = "Name"
            binding.textviewLabelUserName.text = "Username"
            binding.textviewLabelEmail.text = "Email"
            binding.textviewLabelWebsite.text = "Website"

            binding.textviewName.text = user?.name
            binding.textviewUserName.text = user?.username
            binding.textviewUserEmail.text = user?.email
            binding.textviewWebview.text = user?.website
            }
        }

        viewModel.getPostDetail(postId)
    }
}