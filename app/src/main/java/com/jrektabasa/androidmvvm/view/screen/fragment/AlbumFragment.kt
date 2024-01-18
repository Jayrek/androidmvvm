package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jrektabasa.androidmvvm.databinding.FragmentAlbumBinding
import com.jrektabasa.androidmvvm.model.Album
import com.jrektabasa.androidmvvm.view.adapter.AlbumAdapter
import com.jrektabasa.androidmvvm.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter

    private val viewModel: AlbumViewModel by viewModels()
    private val albumList = mutableListOf<Album>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAlbums()

        albumAdapter = AlbumAdapter(albumList)

        viewModel.albums.observe(requireActivity()) { albums ->
            albumList.clear()
            albumList.addAll(albums)
            albumAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = albumAdapter
    }

}