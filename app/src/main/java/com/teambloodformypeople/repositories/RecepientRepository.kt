package com.teambloodformypeople.repositories

import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.RecepientDao
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.network.RecepientApiService
import retrofit2.Response

class RecepientRepository(recepientDao: RecepientDao) {
    fun findAllRecepients(): LiveData<Response<List<Recepient>>> {
        return RecepientApiService.getInstance().findRecepients() as LiveData<Response<List<Recepient>>>
    }
    fun findRecepientById(recepientId: Int): LiveData<Response<Recepient>> {
        return RecepientApiService.getInstance().findByRecepeintIdAsync(recepientId) as LiveData<Response<Recepient>>
    }
    fun insertRecepient(recepient: Recepient) {
        RecepientApiService.getInstance().insertRecepeintAsync(recepient)
    }
    fun updateRecepient(recepient: Recepient) {
        RecepientApiService.getInstance().updateRecepeintAsnc(recepient.id,recepient)
    }
    fun deleteRecepient(recepient: Recepient) {
        RecepientApiService.getInstance().deleteRecepeintAsync(recepient.id)
    }
}

