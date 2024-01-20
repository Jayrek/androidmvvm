package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.FragmentPhotoBinding
import com.jrektabasa.androidmvvm.model.Photo
import com.jrektabasa.androidmvvm.view.adapter.PhotoAdapter
import com.jrektabasa.androidmvvm.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var photoAdapter: PhotoAdapter

    private val photoList = mutableListOf<Photo>()

    private val viewModel: PhotoViewModel by viewModels()

    private val args: PhotoFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = gridLayoutManager

        viewModel.getPhotos(args.albumId)

        photoAdapter = PhotoAdapter(photoList)

        viewModel.photos.observe(requireActivity()) { photos ->
            photoList.clear()
            photoList.addAll(photos)
            photoAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = photoAdapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = gridLayoutManager.childCount
                val totalItemCount: Int = gridLayoutManager.itemCount
                val firstVisibleItemPosition: Int =
                    gridLayoutManager.findFirstVisibleItemPosition()

                if (viewModel.isLoading.value == false
                    && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.getPhotos(args.albumId)
                }
            }
        })
    }
}