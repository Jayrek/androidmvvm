package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.Album
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _albums: MutableLiveData<List<Album>> = MutableLiveData(emptyList())
    val albums: LiveData<List<Album>> = _albums

    fun getAlbums() {
        viewModelScope.launch {
            try {
                val albumResponse = blogRepository.getAlbums()
                _albums.value = albumResponse
            } catch (e: Exception) {
                Log.e("TAG", "getAlbums: ${e.message}")
            } finally {

            }
        }
    }
}