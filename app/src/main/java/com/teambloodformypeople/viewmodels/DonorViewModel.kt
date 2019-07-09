package com.teambloodformypeople.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.network.DonorApiService
import com.teambloodformypeople.repositories.DonorRepository
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.launch
import retrofit2.Response

class DonorViewModel(application: Application) : AndroidViewModel(application){
    val phoneNumber = MutableLiveData("")
    val dateOfBirth = MutableLiveData("")
    val fullName = MutableLiveData("")

    var  _context: Context
    private val donorRepository: DonorRepository

    init {
        val donorApiService =  DonorApiService.getInstance()
        val donorDao= DB.getDatabase(application).donorDao()
        donorRepository = DonorRepository(donorApiService,donorDao,application)
        _context=application
    }
    private val _getResponse = MutableLiveData<Response<Donor>>()
    val getResponse:LiveData<Response<Donor>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<LiveData<List<Donor>>>()
    val getAllResponse:LiveData<LiveData<List<Donor>>>
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

    fun getAllDonors() :LiveData<List<Donor>>{
        return donorRepository.findAllDonorsAsync()
    }

    fun getDonorById(donorId: Int) =viewModelScope.launch{
        _getResponse.postValue(donorRepository.findDonorByIdAsync(donorId))
    }
    fun getDonorByUserId(userId: Int) =viewModelScope.launch{
        _getResponse.postValue(donorRepository.findDonorByUserIdAsync(userId))
    }
    fun insertDonor(donor: TemporaryDonorHolder)  =viewModelScope.launch{
        _insertResponse.postValue(donorRepository.insertDonorAsync(donor))
    }
    fun updateDonor(donor: Donor)  =viewModelScope.launch{
        _updateResponse.postValue(donorRepository.updateDonorAsync(donor))
    }
    fun deleteDonor(donorId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(donorRepository.deleteDonorAsync(donorId))
    }

}