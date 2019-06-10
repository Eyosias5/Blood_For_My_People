package com.teambloodformypeople.hospital

import android.content.Context
import android.content.Intent
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
import com.teambloodformypeople.hospital.adapters.DonorListAdapter
import com.teambloodformypeople.viewmodels.DonorViewModel
import kotlinx.android.synthetic.main.hospital_donor_list.*
import kotlinx.coroutines.Dispatchers

class DonorList : AppCompatActivity() {

    lateinit var model: DonorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_donor_list)
        model = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        donorRecyclerView.layoutManager = linearLayoutManager
//        if(connected()){
            var currentRecepientId = getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE).getInt(Constants().currentRecepient,0)
            if(currentRecepientId==0){
                Toast.makeText(this@DonorList,"User is non-existent", Toast.LENGTH_SHORT).show()
                var i = Intent(applicationContext, Login::class.java)
                startActivity(i)
                finish()
            }
            else {
                model.getAllDonors()
                model.getAllResponse.observe(this, Observer { response ->
                    donorRecyclerView.adapter = DonorListAdapter(response.body()!!)
                })
            }
//        }
    }
    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }
}
