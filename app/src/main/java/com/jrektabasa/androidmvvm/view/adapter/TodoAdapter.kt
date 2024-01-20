package com.jrektabasa.androidmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.TodoItemBinding
import com.jrektabasa.androidmvvm.model.Todo

class TodoAdapter(
    private val todos: MutableList<Todo>,
    private val onItemListener: OnItemListener,
) :
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

        holder.itemBinding.checkboxStatus.text = if (todo.completed) "Done" else "Not Started"
        if (todo.completed) {
            holder.itemBinding.checkboxStatus.isChecked = true
            holder.itemBinding.checkboxStatus.isEnabled = false
            holder.itemBinding.textviewTitle.setTextAppearance(android.R.style.TextAppearance_Medium_Inverse)
        }
        holder.itemBinding.checkboxStatus.setOnCheckedChangeListener { _, isChecked ->
            onItemListener.onCheck(todo.id, isChecked)
        }

    }

    override fun getItemCount(): Int = todos.size

    interface OnItemListener {
        fun onCheck(position: Int, isChecked: Boolean)
    }
}