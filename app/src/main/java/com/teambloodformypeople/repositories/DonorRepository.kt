package com.teambloodformypeople.repositories

import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.DonorDao
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.network.DonorApiService
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DonorRepository(private val DonorApiService: DonorApiService, val donorDao: DonorDao){
    suspend fun findAllDonorsAsync(): LiveData<List<Donor>> {
        withContext(Dispatchers.IO){
            var donorList : List<Donor> = DonorApiService.findDonors().await().body()!!
            donorList.forEach {
                donorDao.insertDonor(it)
            }
        }
        return donorDao.getDonors()
    }

    suspend fun findDonorByIdAsync(donorId: Int): Response<Donor> =
        withContext(Dispatchers.IO){
            DonorApiService.findByDonorIdAsync(donorId).await()
        }
    suspend fun findDonorByUserIdAsync(userId: Int): Response<Donor> =
        withContext(Dispatchers.IO){
            DonorApiService.findByUserIdAsync(userId).await()
        }

    suspend fun insertDonorAsync(donor: TemporaryDonorHolder): Response<Void> =
        withContext(Dispatchers.IO){
            DonorApiService.insertDonorAsync(donor).await()
        }
    suspend fun updateDonorAsync(donor: Donor): Response<Void> =
        withContext(Dispatchers.IO){
            DonorApiService.updateDonorAsnc(donor.donorId,donor).await()
        }
    suspend fun deleteDonorAsync(donorId: Int): Response<Void> =
        withContext(Dispatchers.IO){
            DonorApiService.deleteDonorAsync(donorId).await()
        }
}