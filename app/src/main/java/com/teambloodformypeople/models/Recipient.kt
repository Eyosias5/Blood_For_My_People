package com.teambloodformypeople.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "recipients",
    foreignKeys = [ForeignKey
        (entity = User::class, parentColumns = arrayOf("user_id"), childColumns = arrayOf("recipient_user"))
    ]
)
data class Recipient (
    @PrimaryKey @ColumnInfo(name = "recipient_id", index = true) val id: Int,
    @ColumnInfo(name = "recipient_name") val name: String,
    @ColumnInfo(name = "recipient_location") val location: String,
    @ColumnInfo(name = "recipient_phone_number") val phoneNumber: String,

    @ColumnInfo(name = "recipient_user") val user: Int
): Serializable
