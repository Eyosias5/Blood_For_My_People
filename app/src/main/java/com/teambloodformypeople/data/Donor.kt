package com.teambloodformypeople.data

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "donors",
    foreignKeys = [ForeignKey
        (entity = User::class, parentColumns = arrayOf("user_id"), childColumns = arrayOf("donor_user"))
    ]
)
data class Donor (
    @PrimaryKey @ColumnInfo(name = "donor_id", index = true) val id: Int,
    @ColumnInfo(name = "donor_phone") val phoneNumber: String,
    @ColumnInfo(name = "donor_birth_date") val birthDate: String,
    @ColumnInfo(name = "donor_sex") val sex: String,
    @ColumnInfo(name = "donor_status") val status: String,
    @ColumnInfo(name = "donor_last_donation_date") val lastDonationDate: String,
    @ColumnInfo(name = "donor_blood_type") val bloodType: String,

    @ColumnInfo(name = "donor_user") val user: Int
): Serializable