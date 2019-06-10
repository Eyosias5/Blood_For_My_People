package com.teambloodformypeople.hospital

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import kotlinx.android.synthetic.main.hospital_donation_history_list.*

class DonationHistoryList : AppCompatActivity() {
    lateinit var model: DonationHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_donation_history_list)
        model = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        donationHistoryRecyclerView.layoutManager = linearLayoutManager
        if(connected()){
//            var currentRecepientId = getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE).getInt(Constants().currentRecepient,0)
//            if(currentRecepientId==0){
//                Toast.makeText(this@DonationHistoryList,"User is non-existent",Toast.LENGTH_SHORT).show()
//            }
//            else{
//                model.getAllDonationHistorysByRecepientId(currentRecepientId)
//                model.getAllResponse.observe(this, Observer { response->
//                    response.body().run{
//                        donationHistoryRecyclerView.adapter = DonationHistoryListAdapter(this!!)
//                    }
//                })
//            }
        }
    }

    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }
}
