package com.teambloodformypeople.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.teambloodformypeople.data.models.DonationHistory

@Dao
interface DonationHistoryDao {

    @Query("SELECT * FROM donationHistories Where donationHistoryId = :id")
    fun getDonationHistoryById(id: Int): LiveData<DonationHistory>

    @Query("SELECT * FROM donationHistories")
    fun getDonationHistories(): LiveData<List<DonationHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDonationHistory(donationHistory: DonationHistory)

    @Update
    fun updateDonationHistory(donationHistory: DonationHistory)

    @Delete
    fun deleteDonationHistory(donationHistory: DonationHistory)

    @Query("DELETE FROM donationHistories")
    fun deleteAll()

}