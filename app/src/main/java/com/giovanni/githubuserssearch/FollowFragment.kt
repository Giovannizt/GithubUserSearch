package com.giovanni.githubuserssearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giovanni.githubuserssearch.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private lateinit var _binding: FragmentFollowBinding
    private val binding get() = _binding
    private lateinit var userDetailViewModel: DetailUserViewModel

    companion object {
        const val ARG_POSITION = "Position"
        const val ARG_USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        userDetailViewModel = ViewModelProvider(requireActivity())[DetailUserViewModel::class.java]
        _binding = FragmentFollowBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position = 0
        var username = arguments?.getString(ARG_USERNAME)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        if (position == 1){
            showLoadingUser(true)
            username?.let {userDetailViewModel.getFolllower(it)}
            userDetailViewModel.userFollower.observe(viewLifecycleOwner,{
                setDataFollow(it)
                showLoadingUser(false)
            })
        } else {
            showLoadingUser(true)
            username?.let {userDetailViewModel.getFolllowing(it)}
            userDetailViewModel.userFollowing.observe(viewLifecycleOwner,{
                setDataFollow(it)
                showLoadingUser(false)
            })
        }
    }

    private fun setDataFollow(listUser: List<ItemsItem>){
        binding.apply {
            binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = UsersAdapter(listUser)
            binding.rvFollow.adapter = adapter
        }
    }

    private fun showLoadingUser(isLoading: Boolean) {
        if (isLoading) {
            binding.followProgressBar.visibility = View.VISIBLE
        } else {
            binding.followProgressBar.visibility = View.GONE
        }
    }
}