package com.teambloodformypeople

//import com.teambloodformypeople.adapters.DonationHistoryAdapter
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
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.DonationHistoryAdapter
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import kotlinx.android.synthetic.main.donation_history_fragment.*

class DonationHistoryFragment : Fragment(){
    private lateinit var donationHistoryViewModel: DonationHistoryViewModel
    private lateinit var donorViewModel: DonorViewModel
    private lateinit var recyclerView:RecyclerView
    private lateinit var binding: com.teambloodformypeople.databinding.DonationHistoryFragmentBinding
    private lateinit var donationHistoryAdapter: DonationHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.donation_history_fragment, container, false)
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
            donationHistoryViewModel.getAllDonationHistorysByDonorId(id!!)
            donationHistoryViewModel.getAllResponse.observe(this, Observer { it1 ->
                (binding.recyclerViewHistory.adapter as DonationHistoryAdapter?)?.setDonationHistories(it1.body()!!)
            })

        }
        else if(role=="Recepient"){
            donationHistoryViewModel.getAllDonationHistorysByRecepientId(id!!)
            donationHistoryViewModel.getAllResponse.observe(this, Observer {
                (binding.recyclerViewHistory.adapter as DonationHistoryAdapter?)?.setDonationHistories(it.body()!!)
            })
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = recycler_view_history

        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = donationHistoryAdapter
        val id= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getInt(Constants().currentUser,0)
        val role= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getString(Constants().currentRole,"null")
        if(role=="Donor"){
            donationHistoryViewModel.getAllDonationHistorysByDonorId(id!!)
            donationHistoryViewModel.getAllResponse.observe(this, Observer { it1 ->
                (binding.recyclerViewHistory.adapter as DonationHistoryAdapter?)?.setDonationHistories(it1.body()!!)
            })

        }
        else if(role=="Recepient"){
            donationHistoryViewModel.getAllDonationHistorysByRecepientId(id!!)
            donationHistoryViewModel.getAllResponse.observe(this, Observer {
                (binding.recyclerViewHistory.adapter as DonationHistoryAdapter?)?.setDonationHistories(it.body()!!)
            })
        }

    }
    companion object{
        fun newInstance(): DonationHistoryFragment{
            return DonationHistoryFragment()
        }
    }


}