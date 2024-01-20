package com.jrektabasa.androidmvvm.view.screen.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jrektabasa.androidmvvm.databinding.FragmentTodoUserBinding
import com.jrektabasa.androidmvvm.model.User
import com.jrektabasa.androidmvvm.view.adapter.UserAdapter
import com.jrektabasa.androidmvvm.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoUserFragment : Fragment() {

    private lateinit var binding: FragmentTodoUserBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    private val viewModel: UserViewModel by viewModels()
    private val userList = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridLayoutManager = GridLayoutManager(requireActivity(), 2)
        binding.recyclerView.layoutManager = gridLayoutManager

        viewModel.getUsers()

        userAdapter = UserAdapter(userList, object : UserAdapter.OnItemListener {
            override fun onTap(position: Int) {
                val action = TodoUserFragmentDirections
                    .actionTodoUserFragmentToTodoFragment()
                    .setUserId(userList[position].id)
                findNavController().navigate(action)
            }

        })

        viewModel.users.observe(requireActivity()) { users ->
            userList.clear()
            userList.addAll(users)
            userAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = userAdapter

    }

}