package com.teambloodformypeople.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.DonationHistoryDao
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.network.DonationHistoryApiService
import com.teambloodformypeople.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DonationHistoryRepository(private val donationHistoryApiService: DonationHistoryApiService, val donationHistoryDao: DonationHistoryDao, val application: Application){

     fun findAllDonationHistories(): LiveData<List<DonationHistory>> {
        if(Constants.connected(application )){
            GlobalScope.launch{
                var donationHistories : List<DonationHistory>? = donationHistoryApiService.findDonationHistories().await().body()
                donationHistoryDao.deleteAll()
                donationHistories?.forEach {
                    donationHistoryDao.insertDonationHistory(it)
                }


            }
        }

        return donationHistoryDao.getDonationHistories()
    }

     fun findAllDonationHistoriesByDonorId(donorId: Int): LiveData<List<DonationHistory>>{

        if(Constants.connected(application )){
            GlobalScope.launch{
                var donationHistories : List<DonationHistory>? = donationHistoryApiService.findDonationHistoriesByDonorId(donorId).await().body()

                donationHistories?.forEach {
                    donationHistoryDao.insertDonationHistory(it)
                }


            }
        }

        return donationHistoryDao.getDonationHistoriesByDonor(donorId)
    }

     fun findAllDonationHistoriesByRecepientId(recepientId: Int): LiveData<List<DonationHistory>>{

        if(Constants.connected(application )){
            GlobalScope.launch{
                var donationHistories : List<DonationHistory>? = donationHistoryApiService.findDonationHistoriesByRecepientId(recepientId).await().body()
                donationHistories?.forEach {
                    donationHistoryDao.insertDonationHistory(it)
                }


            }
        }

        return donationHistoryDao.getDonationHistoriesByRecepient(recepientId)
    }
     fun findDonationHistoryAsync(donationHistoryId: Int): LiveData<DonationHistory> {

        if(Constants.connected(application )){
            GlobalScope.launch{
                var donationHistory : DonationHistory = donationHistoryApiService.findByDonationHistoryIdAsync(donationHistoryId).await().body()!!
                donationHistoryDao.insertDonationHistory(donationHistory)

            }

        }
         return donationHistoryDao.getDonationHistoryById(donationHistoryId)
    }


    suspend fun insertDonationHistoryAsync(donationHistory: DonationHistory): Response<Void> =
        withContext(Dispatchers.IO){
            donationHistoryApiService.insertDonationHistoryAsync(donationHistory).await()
        }
    suspend fun updateDonationHistoryAsync(donationHistory: DonationHistory):Response<Void> =
        withContext(Dispatchers.IO){
            donationHistoryApiService.updateDonationHistoryAsnc(donationHistory.donationHistoryId,donationHistory).await()
        }
    suspend fun deleteDonationHistoryAsync(donationHistoryId: Int):Response<Void> =
        withContext(Dispatchers.IO){
            donationHistoryApiService.deleteDonationHistoryAsync(donationHistoryId).await()
        }
}