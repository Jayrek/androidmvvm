package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrektabasa.androidmvvm.databinding.FragmentTodoBinding
import com.jrektabasa.androidmvvm.model.Todo
import com.jrektabasa.androidmvvm.view.adapter.TodoAdapter
import com.jrektabasa.androidmvvm.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel: TodoViewModel by viewModels()
    private val todosList = mutableListOf<Todo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager

        viewModel.getTodos()

        todoAdapter = TodoAdapter(todosList)

        viewModel.todos.observe(requireActivity()) { todos ->
            todosList.clear()
            todosList.addAll(todos)
            todoAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = todoAdapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = linearLayoutManager.childCount
                val totalItemCount: Int = linearLayoutManager.itemCount
                val firstVisibleItemPosition: Int =
                    linearLayoutManager.findFirstVisibleItemPosition()

                if (viewModel.isLoading.value == false
                    && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.getTodos()
                }
            }
        })
    }

}