package com.rasenyer.usersapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasenyer.usersapp.app.UserApplication
import com.rasenyer.usersapp.adapter.UserAdapter
import com.rasenyer.usersapp.databinding.FragmentHomeBinding
import com.rasenyer.usersapp.vm.UserViewModel
import com.rasenyer.usersapp.vm.UserViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory((activity?.application as UserApplication).database.userDao())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val userAdapter = UserAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(username = it.username)
            view.findNavController().navigate(action)
        }

        binding.mRecyclerView.adapter = userAdapter

        lifecycle.coroutineScope.launch {
            userViewModel.getAll().collect {
                userAdapter.submitList(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
