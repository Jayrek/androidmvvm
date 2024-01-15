package com.jrektabasa.androidmvvm.view.screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jrektabasa.androidmvvm.databinding.ActivityPostDetailBinding
import com.jrektabasa.androidmvvm.util.constant.IntentKeys.EXTRA_POST_ID
import com.jrektabasa.androidmvvm.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private val viewModel: PostDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)

        getPostDetails(intent.getIntExtra(EXTRA_POST_ID, -1))
    }

    private fun getPostDetails(postId: Int) {
        lifecycleScope.launch {
            viewModel.getPostDetail(postId)
        }
    }

}