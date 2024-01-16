package com.jrektabasa.androidmvvm.view.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jrektabasa.androidmvvm.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}