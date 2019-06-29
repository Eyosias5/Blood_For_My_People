package com.teambloodformypeople.repositories

import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.network.DonationHistoryApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DonationHistoryRepository(private val DonationHistoryApiService: DonationHistoryApiService) {

    suspend fun findAllDonationHistories(): Response<List<DonationHistory>> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.findDonationHistories().await()
        }
    suspend fun findAllDonationHistoriesByDonorId(donorId: Int): Response<List<DonationHistory>> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.findDonationHistoriesByDonorId(donorId).await()
        }
    suspend fun findAllDonationHistoriesByRecepientId(recepientId: Int): Response<List<DonationHistory>> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.findDonationHistoriesByRecepientId(recepientId).await()
        }

    suspend fun findDonationHistoryAsync(donationHistoryId: Int): Response<DonationHistory> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.findByDonationHistoryIdAsync(donationHistoryId).await()
        }

    suspend fun insertDonationHistoryAsync(donationHistory: DonationHistory): Response<Void> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.insertDonationHistoryAsync(donationHistory).await()
        }
    suspend fun updateDonationHistoryAsync(donationHistory: DonationHistory):Response<Void> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.updateDonationHistoryAsnc(donationHistory.donationHistoryId,donationHistory).await()
        }
    suspend fun deleteDonationHistoryAsync(donationHistoryId: Int):Response<Void> =
        withContext(Dispatchers.IO){
            DonationHistoryApiService.deleteDonationHistoryAsync(donationHistoryId).await()
        }
}