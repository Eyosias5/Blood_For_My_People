package com.teambloodformypeople.viewmodels

import androidx.lifecycle.*
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.network.RecepientApiService
import com.teambloodformypeople.repositories.RecepientRepository
import kotlinx.coroutines.*
import retrofit2.Response

class RecepientViewModel : ViewModel(){
    private val recepientRepository: RecepientRepository

    init {
        val recepientApiService =  RecepientApiService.getInstance()
        recepientRepository = RecepientRepository(recepientApiService)
    }
    private val _getResponse = MutableLiveData<Response<Recepient>>()
    val getResponse:LiveData<Response<Recepient>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<Recepient>>>()
    val getAllResponse:LiveData<Response<List<Recepient>>>
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

    fun getAllRecepients() =viewModelScope.launch{
        _getAllResponse.postValue(recepientRepository.findAllRecepientsAsync())
    }
    fun getRecepientById(recepientId: Int) =viewModelScope.launch{
        _getResponse.postValue(recepientRepository.findRecepientByIdAsync(recepientId))
    }
    fun insertRecepient(recepient: Recepient)  =viewModelScope.launch{
        _insertResponse.postValue(recepientRepository.insertRecepientAsync(recepient))
    }
    fun updateRecepient(recepient: Recepient)  =viewModelScope.launch{
        _updateResponse.postValue(recepientRepository.updateRecepientAsync(recepient))
    }
    fun deleteRecepient(recepientId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(recepientRepository.deleteRecepientAsync(recepientId))
    }

}