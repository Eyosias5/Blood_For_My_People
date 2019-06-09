package et.edu.aait.roomdbexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "donation_histories")

class DonationHistory(
    @PrimaryKey
    @ColumnInfo(name = "donation_history_id")
    val donationHistoryId: Int,
    @ColumnInfo(name = "donor_id")
    val donorId: Int,

    @ColumnInfo(name = "recepient_id")
    val RecipentId: Int,

    val amount: Double,
    val date: String):Serializable {}