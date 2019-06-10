package et.edu.aait.roomdbexample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teambloodformypeople.data.models.DonationHistory

@Dao
interface DonationHistoryDao {

    @Query("SELECT * from donation_histories ORDER BY donation_history_id")
    fun getAllDonationHistory(): LiveData<List<DonationHistory>>

    @Query("SELECT * from donation_histories WHERE donor_id = :donorId ORDER BY donation_history_id")
    fun getAllDonationHistoryByDonor(donorId: Int): LiveData<List<DonationHistory>>

}

