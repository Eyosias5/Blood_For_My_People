package com.teambloodformypeople.viewmodels

import androidx.lifecycle.*
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.network.DonationHistoryApiService
import com.teambloodformypeople.repositories.DonationHistoryRepository
import kotlinx.coroutines.*
import retrofit2.Response

class DonationHistoryViewModel : ViewModel(){
    private val donationHistoryRepository: DonationHistoryRepository

    init {
        val donationHistoryApiService =  DonationHistoryApiService.getInstance()
        donationHistoryRepository = DonationHistoryRepository(donationHistoryApiService)
    }
    private val _getResponse = MutableLiveData<Response<DonationHistory>>()
    val getResponse:LiveData<Response<DonationHistory>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<DonationHistory>>>()
    val getAllResponse:LiveData<Response<List<DonationHistory>>>
        get() = _getAllResponse
    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse:LiveData<Response<Void>>
        get() = _insertResponse
    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse:LiveData<Response<Void>>
        get() = _deleteResponse
    private val _updateResponse = MutableLiveData<Response<Void>>()
    val updateResponse:LiveData<Response<Void>>
        get() = _updateResponse

    fun getAllDonationHistorys() =viewModelScope.launch{
        _getAllResponse.postValue(donationHistoryRepository.findAllDonationHistories())
    }
    fun getDonationHistoryById(donationHistoryId: Int) =viewModelScope.launch{
        _getResponse.postValue(donationHistoryRepository.findDonationHistoryAsync(donationHistoryId))
    }
    fun insertDonationHistory(donationHistory: DonationHistory)  =viewModelScope.launch{
        _insertResponse.postValue(donationHistoryRepository.insertDonationHistoryAsync(donationHistory))
    }
    fun updateDonationHistory(donationHistory: DonationHistory)  =viewModelScope.launch{
        _updateResponse.postValue(donationHistoryRepository.updateDonationHistoryAsync(donationHistory))
    }
    fun deleteDonationHistory(donationHistoryId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(donationHistoryRepository.deleteDonationHistoryAsync(donationHistoryId))
    }

}