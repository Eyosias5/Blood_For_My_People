package com.teambloodformypeople.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.DonationHistoryDao
import com.teambloodformypeople.repositories.DonationHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DonationHistoryViewModel (application: Application): AndroidViewModel(application) {
    private val donationHistoryRepository: DonationHistoryRepository
    private var donationHistoryDao: DonationHistoryDao? = null

    init {
        donationHistoryDao = DB.getDatabase(application).donationHistoryDao()
        donationHistoryRepository = DonationHistoryRepository(donationHistoryDao!!)
    }

    fun getAllDonationHistories(): LiveData<Response<List<DonationHistory>>> {
        return donationHistoryRepository.findAllDonationHistories()
    }
    fun getDonationHistoryById(donationHistoryId : Int): LiveData<Response<DonationHistory>> {
        return donationHistoryRepository.findDonationHistoryById(donationHistoryId)
    }
    fun insertDonationHistory(donationHistory: DonationHistory)  = viewModelScope.launch(Dispatchers.IO) {
        donationHistoryRepository.insertDonationHistory(donationHistory)
    }
    fun updateDonationHistory(donationHistory: DonationHistory)  = viewModelScope.launch(Dispatchers.IO) {
        donationHistoryRepository.updateDonationHistory(donationHistory)
    }
    fun deleteDonationHistory(donationHistory: DonationHistory)  = viewModelScope.launch(Dispatchers.IO) {
        donationHistoryRepository.deleteDonationHistory(donationHistory)
    }

}