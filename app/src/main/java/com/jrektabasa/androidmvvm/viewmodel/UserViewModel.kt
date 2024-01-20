package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.User
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData(emptyList())
    val users: LiveData<List<User>> = _users

    fun getUsers() {
        viewModelScope.launch {
            try {
                val userResponse = blogRepository.getUsers()
                _users.value = userResponse
            } catch (e: Exception) {
                Log.e("TAG", "getUsers: ${e.message}")
            }
        }
    }
}