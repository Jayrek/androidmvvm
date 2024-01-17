package com.jrektabasa.androidmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.CommentItemBinding
import com.jrektabasa.androidmvvm.model.Comment

class PostCommentAdapter(
    private val comments: MutableList<Comment>
) : RecyclerView.Adapter<PostCommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: CommentItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]

        holder.itemBinding.textviewName.text = comment.name
        holder.itemBinding.textviewBody.text = comment.body

    }
}