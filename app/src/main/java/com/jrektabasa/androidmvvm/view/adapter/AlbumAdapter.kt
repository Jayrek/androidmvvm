package com.jrektabasa.androidmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.AlbumItemBinding
import com.jrektabasa.androidmvvm.model.Album

class AlbumAdapter(private val albums: MutableList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]

        holder.itemBinding.textviewTitle.text = album.title
    }

    override fun getItemCount(): Int = albums.size
}


