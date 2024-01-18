package com.jrektabasa.androidmvvm.model

data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)

data class UserAlbum(
    val userId: Int,
    val id: Int,
    val title: String,
    val userName: String)
