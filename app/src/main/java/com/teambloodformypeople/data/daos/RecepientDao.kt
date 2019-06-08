package com.teambloodformypeople.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.teambloodformypeople.data.models.Recepient

@Dao
interface RecepientDao {

    @Query("SELECT * FROM recepients Where recepientId = :id")
    fun getRecipientById(id: Int): LiveData<Recepient>

    @Query("SELECT * FROM recepients")
    fun getRecepients():LiveData<List<Recepient>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipient(recepient: Recepient)

    @Update
    fun updateRecipient(recepient: Recepient)

    @Delete
    fun deleteRecipient(recepient: Recepient)

    @Query("DELETE FROM recepients")
    fun deleteAll()

}