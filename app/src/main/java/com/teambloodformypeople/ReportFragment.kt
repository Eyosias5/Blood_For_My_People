package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.adapters.ReportAdapter
import kotlinx.android.synthetic.main.report_fragment.*

class ReportFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.VISIBLE
        val view = inflater.inflate(R.layout.report_fragment,container,false)

//        val spinner: Spinner = view.bloodType_spinner
//        spinner.onItemSelectedListener = this
//        ArrayAdapter.createFromResource(view.context, R.array.blood_types_array,android.R.layout.simple_spinner_item).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = recycler_view_report
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = context?.let{ ReportAdapter(it) }

    }
    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        parent.getItemAtPosition(pos)
    }
    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    companion object{
        fun newInstance(): ReportFragment{
            return newInstance()
        }
    }
}