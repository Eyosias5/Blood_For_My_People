package com.teambloodformypeople.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambloodformypeople.R
import com.teambloodformypeople.adapters.UserListAdapter
import com.teambloodformypeople.viewmodels.UserViewModel

class RecyclerViewUserListFragment : Fragment(){
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: com.teambloodformypeople.databinding.UserListFragmentBinding
    private lateinit var userListAdapter: UserListAdapter


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
//        var bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
//        bottom_nav.visibility = View.VISIBLE

        binding = DataBindingUtil.inflate(inflater, R.layout.user_list_fragment, container, false)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this

        userListAdapter = UserListAdapter(this.requireContext())
        userListAdapter.setViewModel(userViewModel)
        binding.recyclerViewUserList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerViewUserList.adapter = userListAdapter
        userViewModel.getAllUsers().observe(this, Observer {
            userListAdapter.setUsers(it!!)
        })

//        userViewModel.getAllResponse.observe(this, Observer {
//            users->users?.let {
//                userListAdapter.setUsers(it.value!!)
//            }
//        })
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{
        fun newInstance(): RecyclerViewUserListFragment {
            return RecyclerViewUserListFragment()
        }
    }
}