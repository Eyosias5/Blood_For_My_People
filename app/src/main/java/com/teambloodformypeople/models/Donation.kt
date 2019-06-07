package com.teambloodformypeople.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "donations",
    foreignKeys = [
        ForeignKey(entity = Donor::class, parentColumns = arrayOf("donor_id"), childColumns = arrayOf("donation_donor")),
        ForeignKey(entity = Recipient::class, parentColumns = arrayOf("recipient_id"), childColumns = arrayOf("donation_recipient"))
    ])

data class Donation(
    @PrimaryKey @ColumnInfo(name = "donation_id", index = true) val id: Int,
    @ColumnInfo(name = "donation_date") val date: String,
    @ColumnInfo(name = "donation_amount") val amount: Float,
    @ColumnInfo(name = "donation_blood_type") val bloodType: String,

    @ColumnInfo(name = "donation_donor") val donor: Int,
    @ColumnInfo(name = "donation_recipient") val recipient: Int


):Serializable