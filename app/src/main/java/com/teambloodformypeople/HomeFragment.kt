package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.RecepientsListAdapter
import com.teambloodformypeople.viewmodels.RecepientViewModel
import kotlinx.android.synthetic.main.recepient_fragment.*

class HomeFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    private lateinit var recepientViewModel: RecepientViewModel
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        this.activity?.title = "Hello"
        view?.let { Navigation.findNavController(it).getCurrentDestination()?.setLabel("Hello") }
            val view = inflater.inflate(R.layout.recepient_fragment,container,false)


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE
        recyclerView = recycler_view_home
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{ RecepientsListAdapter(it) }
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
        recepientViewModel.getAllRecepients()
        recepientViewModel.getAllResponse.observe(this, Observer {
            (recyclerView.adapter as RecepientsListAdapter?)?.setRecepients(it.body()!!)
        })
    }
    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

}