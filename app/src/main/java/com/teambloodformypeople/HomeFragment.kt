package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.DonationHistoryAdapter
import com.teambloodformypeople.adapters.HomeAdapter
import kotlinx.android.synthetic.main.donation_history_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        this.activity?.title = "Hello"
        view?.let { Navigation.findNavController(it).getCurrentDestination()?.setLabel("Hello") }
        return inflater.inflate(R.layout.home_fragment,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE
        recyclerView = recycler_view_home
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{ HomeAdapter(it) }

    }
    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

}