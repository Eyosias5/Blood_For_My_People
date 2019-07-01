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
import com.teambloodformypeople.adapters.DonorListAdapter
import com.teambloodformypeople.viewmodels.DonorViewModel

class RecyclerViewDonorListFragment : Fragment(){
    private lateinit var donorViewModel: DonorViewModel
    private lateinit var binding: com.teambloodformypeople.databinding.DonorFragmentBinding
    private lateinit var donorListAdapter: DonorListAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        var bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
        bottom_nav.visibility = View.VISIBLE
        bottom_nav.menu.clear()
        bottom_nav.inflateMenu(R.menu.recepient_bottom_nav_menu)



        binding = DataBindingUtil.inflate(inflater, R.layout.donor_fragment, container, false)
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        binding.viewModel = donorViewModel
        binding.lifecycleOwner = this

        donorListAdapter = DonorListAdapter(this.requireContext())
        donorListAdapter.setViewModel(donorViewModel)
        binding.recyclerViewDonorList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerViewDonorList.adapter = donorListAdapter
        donorViewModel.getAllDonors()
        donorViewModel.getAllResponse.observe(this, Observer {
            donors->donors.let {
                donorListAdapter.setDonors(donors.body()!!)
            }
        })
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{
        fun newInstance(): RecyclerViewDonorListFragment {
            return RecyclerViewDonorListFragment()
        }
    }
}