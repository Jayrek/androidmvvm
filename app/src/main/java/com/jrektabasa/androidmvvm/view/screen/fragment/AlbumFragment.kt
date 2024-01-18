package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.FragmentAlbumBinding
import com.jrektabasa.androidmvvm.model.UserAlbum
import com.jrektabasa.androidmvvm.view.adapter.AlbumAdapter
import com.jrektabasa.androidmvvm.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel: AlbumViewModel by viewModels()
    private val userAlbums = mutableListOf<UserAlbum>()
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
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager

        viewModel.getAlbums()

        albumAdapter = AlbumAdapter(userAlbums)

        viewModel.userAlbums.observe(requireActivity()) { albums ->
            userAlbums.clear()
            userAlbums.addAll(albums)
            albumAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = albumAdapter

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
                    viewModel.getAlbums()
                }
            }
        })
    }

}