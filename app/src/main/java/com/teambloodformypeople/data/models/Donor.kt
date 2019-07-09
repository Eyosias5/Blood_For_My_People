package com.teambloodformypeople.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "donors"
//    foreignKeys = [ForeignKey
//            (entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("userId"))
//    ]
)
data class Donor (
    @PrimaryKey @ColumnInfo(name = "donorId", index = true) var donorId: Int,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,
    @ColumnInfo(name = "dateOfBirth") var dateOfBirth: String,
    @ColumnInfo(name = "fullName") var fullName: String,
    @ColumnInfo(name = "userId") var userId: Int
): Serializable




