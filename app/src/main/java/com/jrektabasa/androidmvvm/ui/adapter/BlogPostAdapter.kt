package com.jrektabasa.androidmvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.R
import com.jrektabasa.androidmvvm.model.Post

class BlogPostAdapter(
    private val context: Context,
    private val posts: List<Post>,
) : RecyclerView.Adapter<BlogPostAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvId = itemView.findViewById<TextView>(R.id.textview_id)
        private val tvTitle = itemView.findViewById<TextView>(R.id.textview_title)
        private val tvBody = itemView.findViewById<TextView>(R.id.textview_body)

        fun bind(post: Post) {
            tvId.text = "Post: ${post.id}"
            tvTitle.text = post.title
            tvBody.text = post.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.blog_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}