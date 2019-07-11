package com.teambloodformypeople.fragments

import android.content.Context
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
import com.teambloodformypeople.adapters.DonationHistoryAdapter
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel

class RecyclerViewDonationHistoryFragment : Fragment(){
    private lateinit var donationHistoryViewModel: DonationHistoryViewModel
    private lateinit var binding: com.teambloodformypeople.databinding.DonationHistoryFragmentBinding
    private lateinit var donationHistoryAdapter: DonationHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
        bottom_nav.visibility = View.VISIBLE


        binding = DataBindingUtil.inflate(inflater,
            R.layout.donation_history_fragment, container, false)
        donationHistoryViewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        binding.viewModel = donationHistoryViewModel
        binding.lifecycleOwner = this


        donationHistoryAdapter = DonationHistoryAdapter(this.requireContext())
        donationHistoryAdapter.setViewModel(donationHistoryViewModel)
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerViewHistory.adapter = donationHistoryAdapter
        val id= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getInt(Constants().currentUser,0)
        val role= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getString(Constants().currentRole,"null")
        if(role=="Donor"){

            val response= donationHistoryViewModel.getAllDonationHistorysByDonorId(id!!)
            response.observe(this, Observer {
                    donationHistories->donationHistories.let {
                donationHistoryAdapter.setDonationHistories(it)
            }
            })


        }
        else if(role=="Recepient"){

            val response = donationHistoryViewModel.getAllDonationHistorysByRecepientId(id!!)
            response.observe(this, Observer {
                    donationHistories->donationHistories.let {
                donationHistoryAdapter.setDonationHistories(it)
            } }
            )

        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{
        fun newInstance(): RecyclerViewDonationHistoryFragment {
            return RecyclerViewDonationHistoryFragment()
        }
    }


}