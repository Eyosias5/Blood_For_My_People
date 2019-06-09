package com.teambloodformypeople.hospital

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.Constants
import com.teambloodformypeople.R
import com.teambloodformypeople.hospital.adapters.ReportListAdapter
import com.teambloodformypeople.viewmodels.ReportViewModel
import kotlinx.android.synthetic.main.hospital_report_list.*

class ReportList : AppCompatActivity() {
    lateinit var model: ReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_donation_history_list)
        model = ViewModelProviders.of(this).get(ReportViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        reportRecyclerView.layoutManager = linearLayoutManager
        if(connected()){
            var currentRecepientId = getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE).getInt(
                Constants().currentRecepient,0)
            if(currentRecepientId==0){
                Toast.makeText(this@ReportList,"User is non-existent", Toast.LENGTH_SHORT).show()
            }
            else {
                model.getAllReports()
                model.getAllResponse.observe(this, Observer { response ->
                    response.body().run {
                        reportRecyclerView.adapter = ReportListAdapter(this!!)
                    }
                })
            }
        }
    }

    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }
}
