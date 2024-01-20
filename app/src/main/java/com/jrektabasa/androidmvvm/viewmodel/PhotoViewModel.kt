package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.Photo
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val blogRepository: BlogRepository,
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData(emptyList())
    val photos: LiveData<List<Photo>> = _photos

    private var currentPage = 1
    fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val photoResponse = blogRepository.getPhoto(
                    albumId = albumId,
                    page = currentPage,
                )

                currentPage += 1
                val blogPhotos = _photos.value ?: emptyList()
                _photos.value = blogPhotos + photoResponse

            } catch (e: Exception) {
                Log.e("TAG", "getPhotos: ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }
    }
}