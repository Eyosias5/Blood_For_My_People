package com.teambloodformypeople.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.RecepientDao
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.network.RecepientApiService
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.util.TemporaryRecepientHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RecepientRepository(private val RecepientApiService: RecepientApiService, val recepientDao: RecepientDao, val application: Application){
    suspend fun findAllRecepientsAsync(): Response<List<Recepient>> =
        withContext(Dispatchers.IO){
            RecepientApiService.findRecepients().await()
        }
     fun findRecepientByIdAsync(recepientId: Int): LiveData<Recepient> {
         if(Constants.connected(application)){
             GlobalScope.launch{
                 var recepient : Recepient = RecepientApiService.findByRecepeintIdAsync(recepientId).await().body()!!

                 recepientDao.insertRecipient(recepient)

             }
         }

         return recepientDao.getRecipientById(recepientId)

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
            RecepientApiService.updateRecepeintAsnc(recepient.recepientId,recepient).await()
        }
    suspend fun deleteRecepientAsync(recepientId: Int): Response<Void> =
        withContext(Dispatchers.IO){
            RecepientApiService.deleteRecepeintAsync(recepientId).await()
        }
}