package com.teambloodformypeople.data.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "reports",
    foreignKeys = [ForeignKey
        (entity = DonationHistory::class, parentColumns = arrayOf("donationHistoryId"), childColumns = arrayOf("donationHistoryId"))
    ]
)
data class Report (
    @PrimaryKey @ColumnInfo(name = "reportId", index = true) val id: Int,
    @ColumnInfo(name = "donationHistoryId") val donationHistoryId: Int,

    @ColumnInfo(name = "bloodType") val bloodType: String
): Serializable
