package com.jrektabasa.androidmvvm.view.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jrektabasa.androidmvvm.databinding.PhotoItemBinding
import com.jrektabasa.androidmvvm.model.Photo

class PhotoAdapter(
    private val photos: MutableList<Photo>,
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val view = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        val photo = photos[position]

        holder.itemBinding.textviewTitle.text = photo.title
        Glide.with(holder.itemBinding.root)
            .load(photo.thumbnailUrl)
            .placeholder(ColorDrawable(Color.LTGRAY))
            .error(ColorDrawable(Color.GRAY))
            .into(holder.itemBinding.imageview)
    }

    override fun getItemCount(): Int = photos.size
}