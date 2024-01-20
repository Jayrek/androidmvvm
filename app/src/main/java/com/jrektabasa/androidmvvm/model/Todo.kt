package com.jrektabasa.androidmvvm.model

data class Todo(
    val userID: Int,
    val id: Int,
    val title: String,
    var completed: Boolean
)
