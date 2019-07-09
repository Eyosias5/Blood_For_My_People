package com.teambloodformypeople.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.teambloodformypeople.data.models.Recepient
import java.io.Serializable

@Entity(tableName = "donationHistories"
//    foreignKeys = [
//        ForeignKey(entity = Donor::class, parentColumns = arrayOf("donorId"), childColumns = arrayOf("recepientId")),
//        ForeignKey(entity = Recepient::class, parentColumns = arrayOf("recepientId"), childColumns = arrayOf("recepientId"))
//    ]
)

data class DonationHistory (
    @PrimaryKey @ColumnInfo(name = "donationHistoryId", index = true) var donationHistoryId: Int,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "amount") var amount: Float,

    @ColumnInfo(name = "donorId") var donorId: Int,
    @ColumnInfo(name = "recepientId") var recepientId: Int

): Serializable
