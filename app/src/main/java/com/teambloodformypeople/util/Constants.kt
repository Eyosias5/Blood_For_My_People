package com.teambloodformypeople.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Constants {
    val baseUrl = "http://192.168.43.242:3434/api/"
    val currentUser = "CURRENT_USER"
    val currentRole = "CURRENT_ROLE"

    companion object{
        fun connected(application: Application):Boolean {

            val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

            return networkInfo != null && networkInfo.isConnected

        }
    }
}