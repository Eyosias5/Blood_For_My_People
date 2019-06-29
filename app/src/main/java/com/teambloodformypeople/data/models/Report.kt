package com.teambloodformypeople.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "reports",
    foreignKeys = [ForeignKey
        (entity = DonationHistory::class, parentColumns = arrayOf("donationHistoryId"), childColumns = arrayOf("donationHistoryId"))
    ]
)
data class Report (
    @PrimaryKey @ColumnInfo(name = "reportId", index = true) var reportId: Int,
    @ColumnInfo(name = "donationHistoryId", index = true) var donationHistoryId: Int,

    @ColumnInfo(name = "bloodType") var bloodType: String
): Serializable
