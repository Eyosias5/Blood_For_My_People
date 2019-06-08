package com.teambloodformypeople.repositories

import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.DonorDao
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.network.DonorApiService
import retrofit2.Response

class DonorRepository(donorDao: DonorDao) {
    fun findAllDonors(): LiveData<Response<List<Donor>>> {
        return DonorApiService.getInstance().findDonors() as LiveData<Response<List<Donor>>>
    }
    fun findDonorById(donorId: Int): LiveData<Response<Donor>> {
        return DonorApiService.getInstance().findByDonorIdAsync(donorId) as LiveData<Response<Donor>>
    }
    fun insertDonor(donor: Donor) {
        DonorApiService.getInstance().insertDonorAsync(donor)
    }
    fun updateDonor(donor: Donor) {
        DonorApiService.getInstance().updateDonorAsnc(donor.id,donor)
    }
    fun deleteDonor(donor: Donor) {
        DonorApiService.getInstance().deleteDonorAsync(donor.id)
    }

}