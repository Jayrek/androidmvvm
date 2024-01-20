package com.jrektabasa.androidmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.PostItemBinding
import com.jrektabasa.androidmvvm.model.Post

class PostAdapter(
    private val posts: MutableList<Post>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: PostItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        holder.itemBinding.textviewTitle.text = post.title
        holder.itemBinding.textviewBody.text = post.body

        holder.itemBinding.constraintBlogItem.setOnClickListener {
            onItemClickListener.onClick(post)
        }
    }

    interface OnItemClickListener {
        fun onClick(post: Post)
    }
}