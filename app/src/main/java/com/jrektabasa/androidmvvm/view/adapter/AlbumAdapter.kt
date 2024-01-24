package com.jrektabasa.androidmvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.R
import com.jrektabasa.androidmvvm.databinding.AlbumItemBinding
import com.jrektabasa.androidmvvm.model.UserAlbum

class AlbumAdapter(
    private val context: Context,
    private val albums: MutableList<UserAlbum>,
    private val onItemListener: OnItemListener,
) :
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
        holder.itemBinding.textviewUserName.text = context.getString(R.string.by, album.userName)
        holder.itemBinding.cardView.setOnClickListener {
            onItemListener.onTap(position)
        }
    }

    override fun getItemCount(): Int = albums.size

    interface OnItemListener {
        fun onTap(position: Int)
    }
}


