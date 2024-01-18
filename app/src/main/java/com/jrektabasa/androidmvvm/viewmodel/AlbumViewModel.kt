package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.UserAlbum
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _userAlbums: MutableLiveData<List<UserAlbum>> = MutableLiveData(emptyList())
    val userAlbums: LiveData<List<UserAlbum>> = _userAlbums

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var currentPage = 1

    private var userAlbumList = mutableListOf<UserAlbum>()

    fun getAlbums() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val albumResponse = blogRepository.getAlbums(page = currentPage)

                if (albumResponse.isEmpty()) return@launch

                val usersResponse = blogRepository.getUsers()
                val blogAlbums = _userAlbums.value ?: emptyList()
                _userAlbums.value = blogAlbums + userAlbumList

                for (album in albumResponse) {
                    for (user in usersResponse) {
                        if (album.userId == user.id) {
                            userAlbumList.add(
                                UserAlbum(
                                    userId = album.userId,
                                    id = album.id,
                                    title = album.title,
                                    userName = user.name
                                )
                            )
                            _userAlbums.value = userAlbumList
                        }
                    }
                }
                currentPage += 1

            } catch (e: Exception) {
                Log.e("TAG", "getAlbums: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}