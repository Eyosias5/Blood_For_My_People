package com.teambloodformypeople

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.DonationHistoryAdapter
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import kotlinx.android.synthetic.main.donation_history_fragment.*

class DonationHistoryFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    private lateinit var donationHistoryViewModel: DonationHistoryViewModel
    private lateinit var donorViewModel: DonorViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE
        return inflater.inflate(R.layout.donation_history_fragment,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = recycler_view_history
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{DonationHistoryAdapter(it)}
        donationHistoryViewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        val id= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getInt(Constants().currentUser,0)
        val role= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getString(Constants().currentRole,"null")
        if(role=="Donor"){
            donationHistoryViewModel.getAllDonationHistorysByDonorId(id!!)
            donationHistoryViewModel.getAllResponse.observe(this, Observer { it1 ->
                (recyclerView.adapter as DonationHistoryAdapter?)?.setDonationHistories(it1.body()!!)
            })

        }
        else if(role=="Recepient"){
            donationHistoryViewModel.getAllDonationHistorysByRecepientId(id!!)
            donationHistoryViewModel.getAllResponse.observe(this, Observer {
                (recyclerView.adapter as DonationHistoryAdapter?)?.setDonationHistories(it.body()!!)
            })
        }

    }
    companion object{
        fun newInstance(): DonationHistoryFragment{
            return DonationHistoryFragment()
        }
    }


}