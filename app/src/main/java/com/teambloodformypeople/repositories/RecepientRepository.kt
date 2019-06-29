package com.teambloodformypeople.repositories

import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.network.RecepientApiService
import com.teambloodformypeople.util.TemporaryRecepientHolder
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
    suspend fun findRecepientByUserIdAsync(userId: Int): Response<Recepient> =
        withContext(Dispatchers.IO){
            RecepientApiService.findByUserIdAsync(userId).await()
        }
    suspend fun insertRecepientAsync(recepient: TemporaryRecepientHolder): Response<Void> =
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