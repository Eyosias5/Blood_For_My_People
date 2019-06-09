package com.teambloodformypeople.hospital

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.hospital.adapters.DonorListAdapter
import com.teambloodformypeople.viewmodels.DonorViewModel
import kotlinx.android.synthetic.main.hospital_donor_list.*

class DonorList : AppCompatActivity() {

    lateinit var model: DonorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_donor_list)
        model = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager
        if(connected()){
            model.getAllDonors()
            model.getAllResponse.observe(this, Observer { response->
                response.body().run{
                    recyclerView.adapter = DonorListAdapter(this!!)
                }

            })

        }

    }
    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }
}
