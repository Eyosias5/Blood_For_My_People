package com.teambloodformypeople


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.DonorListAdapter
import com.teambloodformypeople.adapters.HomeAdapter
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_donor_list.*


class DonorListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    private lateinit var donorViewModel: DonorViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donor_list, container, false)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recycler_view_donor_list
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{ DonorListAdapter(it) }
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        donorViewModel.getAllDonors()
        donorViewModel.getAllResponse.observe(this, Observer {
            (recyclerView.adapter as DonorListAdapter?)?.setDonors(it.body()!!)
        })
    }
    companion object{
        fun newInstance(): DonorListFragment{
            return DonorListFragment()
        }
    }
}
