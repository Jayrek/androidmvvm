package com.jrektabasa.androidmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jrektabasa.androidmvvm.R
import com.jrektabasa.androidmvvm.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }

    private fun getPosts() {
        lifecycleScope.launch {
            viewModel.getPosts()
        }
    }
}