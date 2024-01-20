package com.jrektabasa.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrektabasa.androidmvvm.model.Todo
import com.jrektabasa.androidmvvm.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _todos: MutableLiveData<List<Todo>> = MutableLiveData(emptyList())
    val todos: LiveData<List<Todo>> = _todos

    fun getTodos(userId: Int) {
        viewModelScope.launch {
            try {
                val todoResponse = blogRepository.getTodos(
                    userId,
                )
                _todos.value = todoResponse
            } catch (e: Exception) {
                Log.e("TAG", "getTodos: ${e.message}")
                _todos.value = emptyList()
            }
        }
    }
}