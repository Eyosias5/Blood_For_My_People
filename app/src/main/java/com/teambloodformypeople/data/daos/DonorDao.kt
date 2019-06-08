package com.teambloodformypeople.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.teambloodformypeople.data.models.Donor

@Dao
interface DonorDao {

    @Query("SELECT * FROM donors Where donorId = :id")
    fun getDonorById(id: Int): LiveData<Donor>

    @Query("SELECT * FROM donors")
    fun getDonors():LiveData<List<Donor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDonor(donor: Donor)

    @Update
    fun updateDonor(donor: Donor)

    @Delete
    fun deleteDonor(donor: Donor)

    @Query("DELETE FROM donors")
    fun deleteAll()

}