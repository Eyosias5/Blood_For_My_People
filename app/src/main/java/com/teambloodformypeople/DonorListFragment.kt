package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.DonorListAdapter
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import kotlinx.android.synthetic.main.donor_fragment.*

class DonorListFragment : Fragment(){
    lateinit var recyclerView: RecyclerView

    private lateinit var binding: com.teambloodformypeople.databinding.DonorFragmentBinding

    private lateinit var viewModel: DonorViewModel
    private lateinit var donationHistoryViewModel : DonationHistoryViewModel
    private lateinit var recepientViewModel: RecepientViewModel

    private lateinit var donorListAdapter: DonorListAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        this.activity?.title = "Hello"
        view?.let { Navigation.findNavController(it).currentDestination?.setLabel("Hello") }
        binding = DataBindingUtil.inflate(inflater, R.layout.donor_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        donationHistoryViewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE

        donorListAdapter = DonorListAdapter(this.requireContext())
        donorListAdapter.setViewModel(viewModel)
        binding.recyclerViewDonorList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerViewDonorList.adapter = donorListAdapter
        viewModel.getAllDonors()
        viewModel.getAllResponse.observe(this, Observer {
                donors->donors?.let { donorListAdapter.setDonors(donors.body()!!) }
        })
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE
        recyclerView = recycler_view_donor_list
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{ DonorListAdapter(it) }
        viewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        viewModel.getAllDonors()
        viewModel.getAllResponse.observe(this, Observer {
            (recyclerView.adapter as DonorListAdapter?)?.setDonors(it.body()!!)
        })

    }
    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }
}