package com.teambloodformypeople.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "donors"
//    foreignKeys = [ForeignKey
//            (entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("userId"))
//    ]
)
data class Donor (
    @PrimaryKey @ColumnInfo(name = "donorId", index = true) val donorId: Int,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "dateOfBirth") val dateOfBirth: String,
    @ColumnInfo(name = "fullName") val fullName: String,
    @ColumnInfo(name = "userId", index = true) val userId: Int
): Serializable




