package com.teambloodformypeople.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.DonationHistoryDao

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import com.teambloodformypeople.data.models.DonationHistory

class DonationHistoryRepository(private val donationHistoryDao: DonationHistoryDao) {

//    fun allDonationHistories(): LiveData<List<DonationHistory>> = donationHistoryDao.getAllDonationHistory()

//    fun insertCourse(course: Course) {
//        donationHistoryDao.insertDonationHistory(course)
//
//    }
}


