package com.teambloodformypeople.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import et.edu.aait.roomdbexample.data.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DonationHistoryRepository(private val donationHistoryDao: DonationHistoryDao) {

    fun allDonationHistories(): LiveData<List<DonationHistory>> = donationHistoryDao.getAllDonationHistory()

//    fun insertCourse(course: Course) {
//        donationHistoryDao.insertDonationHistory(course)
//
//    }
}


