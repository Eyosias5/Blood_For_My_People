package com.teambloodformypeople.viewmodels

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.network.DonationHistoryApiService
import com.teambloodformypeople.repositories.DonationHistoryRepository
import com.teambloodformypeople.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class DonationHistoryViewModel(application: Application) : AndroidViewModel(application){
    private val donationHistoryRepository: DonationHistoryRepository

    val donorId = MutableLiveData(0)
    var amount = MutableLiveData("")
    val donationHistoryId = MutableLiveData(0)

    var  _context: Context
    init {
        val donationHistoryApiService =  DonationHistoryApiService.getInstance()
        donationHistoryRepository = DonationHistoryRepository(donationHistoryApiService)
        _context=application
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
    fun getAllDonationHistorysByDonorId(donorId: Int) =viewModelScope.launch{
        _getAllResponse.postValue(donationHistoryRepository.findAllDonationHistoriesByDonorId(donorId))
    }
    fun getAllDonationHistorysByRecepientId(recepientId: Int) =viewModelScope.launch{
        _getAllResponse.postValue(donationHistoryRepository.findAllDonationHistoriesByRecepientId(recepientId))
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
    fun insertDonationHistory(view: View, deleteChecker: Boolean) {
        viewModelScope.launch{
            if(deleteChecker){
                if (donationHistoryId.value!=0) {
                    deleteDonationHistory(donationHistoryId=donationHistoryId.value!!)
                    Toast.makeText(view.context, "Donation History is deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view.context, "Donation History doesn't exit", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                var recepientId = _context.getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE)?.getInt(Constants().currentUser,0)
                if(recepientId!=0){
                    if (donationHistoryId.value!=0) {
                        updateDonationHistory(DonationHistory(donationHistoryId = donationHistoryId.value!!, date= "",donorId = donorId.value!!,recepientId = recepientId!!, amount = amount.value!!.toFloat()))
                        Toast.makeText(view.context, "Donation History is updated", Toast.LENGTH_SHORT).show()
                    } else {
                        insertDonationHistory(DonationHistory(donationHistoryId = donationHistoryId.value!!, date= "",donorId = donorId.value!!,recepientId = recepientId!!, amount = amount.value!!.toFloat()))
                        Toast.makeText(view.context, "Donation History is added", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}