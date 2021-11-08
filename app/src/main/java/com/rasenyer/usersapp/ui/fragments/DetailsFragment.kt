package com.rasenyer.usersapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasenyer.usersapp.app.UserApplication
import com.rasenyer.usersapp.adapter.UserAdapter
import com.rasenyer.usersapp.databinding.FragmentDetailsBinding
import com.rasenyer.usersapp.vm.UserViewModel
import com.rasenyer.usersapp.vm.UserViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsFragment: Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object { var USERNAME = "username" }
    private lateinit var username: String

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory((activity?.application as UserApplication).database.userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { username = it.getString(USERNAME).toString() }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val userAdapter = UserAdapter {}

        binding.mRecyclerView.adapter = userAdapter

        lifecycle.coroutineScope.launch {
            userViewModel.getByUsername(username).collect {
                userAdapter.submitList(it)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
