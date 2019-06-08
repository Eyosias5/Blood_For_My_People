package com.teambloodformypeople.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.RecepientDao
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.repositories.RecepientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RecepientViewModel  (application: Application): AndroidViewModel(application) {
    private val recepientRepository: RecepientRepository
    private var recepientDao: RecepientDao? = null

    init {
        recepientDao = DB.getDatabase(application).recipientDao()
        recepientRepository = RecepientRepository(recepientDao!!)
    }

    fun getAllRecepients(): LiveData<Response<List<Recepient>>> {
        return recepientRepository.findAllRecepients()
    }
    fun getRecepientById(recepientId : Int): LiveData<Response<Recepient>> {
        return recepientRepository.findRecepientById(recepientId)
    }
    fun insertRecepient(recepient: Recepient)  = viewModelScope.launch(Dispatchers.IO) {
        recepientRepository.insertRecepient(recepient)
    }
    fun updateRecepient(recepient: Recepient)  = viewModelScope.launch(Dispatchers.IO) {
        recepientRepository.updateRecepient(recepient)
    }
    fun deleteRecepient(recepient: Recepient)  = viewModelScope.launch(Dispatchers.IO) {
        recepientRepository.deleteRecepient(recepient)
    }
}