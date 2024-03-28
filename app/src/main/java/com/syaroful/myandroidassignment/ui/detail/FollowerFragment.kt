package com.syaroful.myandroidassignment.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.syaroful.myandroidassignment.databinding.FragmentFollowerBinding
import com.syaroful.myandroidassignment.ui.ViewModelFactory
import com.syaroful.myandroidassignment.ui.home.UserAdapter

class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//      initiate viewmodelfactory
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireContext())
        val viewModel: UserDetailViewModel by viewModels {
            factory
        }

        val recycleView = binding.rvFollowers
        val adapter = UserAdapter()
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter


        val username = arguments?.getString("login") ?: ""

        viewModel.findUserFollowers(username)
        viewModel.userFollowers.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}