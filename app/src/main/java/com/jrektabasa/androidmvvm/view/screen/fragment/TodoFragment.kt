package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val args: TodoFragmentArgs by navArgs()

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

        viewModel.getTodos(args.userId)

        todoAdapter = TodoAdapter(todosList, object : TodoAdapter.OnItemListener {
            override fun onCheck(position: Int, isChecked: Boolean) {
                if(isChecked){
                    Log.d("isChecked", "onCheck: $isChecked")
//                    todosList[position].completed = true
//                    todoAdapter.notifyItemChanged(position)
                }else {

                    Log.d("!isChecked", "onCheck: $isChecked")
                }
            }

        })

        viewModel.todos.observe(requireActivity()) {
            todosList.clear()
            todosList.addAll(it)
            todoAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = todoAdapter
    }

}