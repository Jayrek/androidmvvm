package com.jrektabasa.androidmvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.BlogItemBinding
import com.jrektabasa.androidmvvm.model.Post

class BlogPostAdapter(
    private val context: Context,
    private val posts: MutableList<Post>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BlogPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = BlogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: BlogItemBinding) :
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