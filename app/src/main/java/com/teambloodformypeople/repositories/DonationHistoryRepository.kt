package com.teambloodformypeople.repositories

import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.daos.DonationHistoryDao
import com.teambloodformypeople.network.DonationHistoryApiService
import retrofit2.Response

class DonationHistoryRepository(donationHistoryDao: DonationHistoryDao) {
    fun findAllDonationHistories(): LiveData<Response<List<DonationHistory>>> {
        return DonationHistoryApiService.getInstance().findDonationHistories() as LiveData<Response<List<DonationHistory>>>
    }
    fun findDonationHistoryById(donationHistoryId: Int): LiveData<Response<DonationHistory>> {
        return DonationHistoryApiService.getInstance().findByDonationHistoryIdAsync(donationHistoryId) as LiveData<Response<DonationHistory>>
    }
    fun insertDonationHistory(donationHistory: DonationHistory) {
        DonationHistoryApiService.getInstance().insertDonationHistoryAsync(donationHistory)
    }
    fun updateDonationHistory(donationHistory: DonationHistory) {
        DonationHistoryApiService.getInstance().updateDonationHistoryAsnc(donationHistory.id,donationHistory)
    }
    fun deleteDonationHistory(donationHistory: DonationHistory) {
        DonationHistoryApiService.getInstance().deleteDonationHistoryAsync(donationHistory.id)
    }
}