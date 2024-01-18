package com.jrektabasa.androidmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.TodoItemBinding
import com.jrektabasa.androidmvvm.model.Todo

class TodoAdapter(private val todos: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val view = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(var itemBinding: TodoItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val todo = todos[position]

        holder.itemBinding.textviewTitle.text = todo.title
        holder.itemBinding.textviewCompleted.text = todo.completed.toString()
    }

    override fun getItemCount(): Int = todos.size
}