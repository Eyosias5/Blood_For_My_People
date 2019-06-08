package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.ReportAdapter
import kotlinx.android.synthetic.main.donation_history_fragment.*
import kotlinx.android.synthetic.main.report_fragment.*

class ReportFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.report_fragment,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = recycler_view_report
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{ ReportAdapter(it) }

    }
    companion object{
        fun newInstance(): ReportFragment{
            return newInstance()
        }
    }
}