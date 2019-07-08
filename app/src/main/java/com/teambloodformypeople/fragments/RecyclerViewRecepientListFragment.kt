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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambloodformypeople.R
import com.teambloodformypeople.adapters.RecepientsListAdapter
import com.teambloodformypeople.viewmodels.RecepientViewModel

class RecyclerViewRecepientListFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    private lateinit var recepientViewModel: RecepientViewModel
    private lateinit var binding: com.teambloodformypeople.databinding.RecepientFragmentBinding
    private lateinit var viewModel: RecepientViewModel
    private lateinit var recepientsListAdapter: RecepientsListAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        var bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
        bottom_nav.visibility = View.VISIBLE
        bottom_nav.menu.clear()
        bottom_nav.inflateMenu(R.menu.donor_bottom_nav_menu)

        binding = DataBindingUtil.inflate(inflater, R.layout.recepient_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        recepientsListAdapter = RecepientsListAdapter(this.requireContext())
        recepientsListAdapter.setViewModel(viewModel)
        binding.recyclerViewRecepientList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerViewRecepientList.adapter = recepientsListAdapter
        viewModel.getAllRecepients()
        viewModel.getAllResponse.observe(this, Observer {
                recepients->recepients?.let { recepientsListAdapter.setRecepients(recepients.body()!!) }
        })
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{
        fun newInstance(): RecyclerViewRecepientListFragment {
            return RecyclerViewRecepientListFragment()
        }
    }

}