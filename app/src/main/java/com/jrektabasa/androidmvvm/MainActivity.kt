package com.jrektabasa.androidmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jrektabasa.androidmvvm.ui.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel = PostViewModel()
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]
    }
}