package com.teambloodformypeople.repositories

import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.RecepientDao
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.RecepientApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RecepientRepository(private val RecepientApiService: RecepientApiService) {
    suspend fun findAllRecepientsAsync(): Response<List<Recepient>> =
        withContext(Dispatchers.IO){
            RecepientApiService.findRecepients().await()
        }
    suspend fun findRecepientByIdAsync(recepientId: Int): Response<Recepient> =
        withContext(Dispatchers.IO){
            RecepientApiService.findByRecepeintIdAsync(recepientId).await()
        }

    suspend fun insertRecepientAsync(recepient: Recepient): Response<Void> =
        withContext(Dispatchers.IO){
            RecepientApiService.insertRecepeintAsync(recepient).await()
        }
    suspend fun updateRecepientAsync(recepient: Recepient): Response<Void> =
        withContext(Dispatchers.IO){
            RecepientApiService.updateRecepeintAsnc(recepient.id,recepient).await()
        }
    suspend fun deleteRecepientAsync(recepientId: Int): Response<Void> =
        withContext(Dispatchers.IO){
            RecepientApiService.deleteRecepeintAsync(recepientId).await()
        }
}