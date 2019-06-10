package com.teambloodformypeople.data.models

import androidx.room.*
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.data.models.Recepient
import java.io.Serializable

@Entity(tableName = "donationHistories",
    foreignKeys = [
        ForeignKey(entity = Donor::class, parentColumns = arrayOf("donorId"), childColumns = arrayOf("recepientId")),
        ForeignKey(entity = Recepient::class, parentColumns = arrayOf("recepientId"), childColumns = arrayOf("recepientId"))
    ]
)

data class DonationHistory (
    @PrimaryKey @ColumnInfo(name = "donationHistoryId", index = true) val id: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "amount") val amount: Float,

    @ColumnInfo(name = "donorId", index = true) val donorId: Int,
    @ColumnInfo(name = "recepientId", index = true) val recipientId: Int

): Serializable
