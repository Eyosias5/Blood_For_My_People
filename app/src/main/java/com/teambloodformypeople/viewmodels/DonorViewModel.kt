package com.teambloodformypeople.viewmodels

import androidx.lifecycle.*
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.network.DonorApiService
import com.teambloodformypeople.repositories.DonorRepository
import kotlinx.coroutines.*
import retrofit2.Response

class DonorViewModel : ViewModel(){
    private val donorRepository: DonorRepository

    init {
        val donorApiService =  DonorApiService.getInstance()
        donorRepository = DonorRepository(donorApiService)
    }
    private val _getResponse = MutableLiveData<Response<Donor>>()
    val getResponse:LiveData<Response<Donor>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<Donor>>>()
    val getAllResponse:LiveData<Response<List<Donor>>>
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

    fun getAllDonors() =viewModelScope.launch{
        _getAllResponse.postValue(donorRepository.findAllDonorsAsync())
    }
    fun getDonorById(donorId: Int) =viewModelScope.launch{
        _getResponse.postValue(donorRepository.findDonorByIdAsync(donorId))
    }
    fun insertDonor(donor: Donor)  =viewModelScope.launch{
        _insertResponse.postValue(donorRepository.insertDonorAsync(donor))
    }
    fun updateDonor(donor: Donor)  =viewModelScope.launch{
        _updateResponse.postValue(donorRepository.updateDonorAsync(donor))
    }
    fun deleteDonor(donorId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(donorRepository.deleteDonorAsync(donorId))
    }

}