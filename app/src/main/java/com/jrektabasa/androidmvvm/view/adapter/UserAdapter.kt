package com.jrektabasa.androidmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.UserItemBinding
import com.jrektabasa.androidmvvm.model.User

class UserAdapter(
    private val users: MutableList<User>,
    private val onItemListener: OnItemListener,
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = users[position]

        holder.itemBinding.textviewTodoLabel.text = "TODO(s) by:"
        holder.itemBinding.textviewUserName.text = user.name
        holder.itemBinding.cardView.setOnClickListener {
            onItemListener.onTap(user.id)
        }
    }

    override fun getItemCount(): Int = users.size

    interface OnItemListener {
        fun onTap(position: Int)
    }
}