package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.DonationHistoryAdapter
import kotlinx.android.synthetic.main.donation_history_fragment.*

class DonationHistoryFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
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

    }
    companion object{
        fun newInstance(): DonationHistoryFragment{
            return DonationHistoryFragment()
        }
    }
}