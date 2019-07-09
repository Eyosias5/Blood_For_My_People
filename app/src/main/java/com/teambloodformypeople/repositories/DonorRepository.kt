package com.teambloodformypeople.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.DonorDao
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.network.DonorApiService
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DonorRepository(private val DonorApiService: DonorApiService, val donorDao: DonorDao, val application: Application){
     fun findAllDonorsAsync(): LiveData<List<Donor>> {
        if(Constants.connected(application)){
            GlobalScope.launch{
                var donorList : List<Donor> = DonorApiService.findDonors().await().body()!!
                donorList.forEach {
                    donorDao.insertDonor(it)
                }
            }
        }

        return donorDao.getDonors()
    }

     fun findDonorByIdAsync(donorId: Int): LiveData<Donor> {
        if(Constants.connected(application)){
            GlobalScope.launch{
                var donor : Donor = DonorApiService.findByDonorIdAsync(donorId).await().body()!!

                    donorDao.insertDonor(donor)

            }
        }

        return donorDao.getDonorById(donorId)

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