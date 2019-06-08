package com.teambloodformypeople.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.DonorDao
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.repositories.DonorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DonorViewModel (application: Application): AndroidViewModel(application) {
    private val donorRepository: DonorRepository
    private var donorDao: DonorDao? = null

    init {
        donorDao = DB.getDatabase(application).donorDao()
        donorRepository = DonorRepository(donorDao!!)
    }

    fun getAllDonors(): LiveData<Response<List<Donor>>> {
        return donorRepository.findAllDonors()
    }
    fun getDonorById(donorId : Int): LiveData<Response<Donor>> {
        return donorRepository.findDonorById(donorId)
    }
    fun insertDonor(donor: Donor)  = viewModelScope.launch(Dispatchers.IO) {
        donorRepository.insertDonor(donor)
    }
    fun updateDonor(donor: Donor)  = viewModelScope.launch(Dispatchers.IO) {
        donorRepository.updateDonor(donor)
    }
    fun deleteDonor(donor: Donor)  = viewModelScope.launch(Dispatchers.IO) {
        donorRepository.deleteDonor(donor)
    }
}