package com.teambloodformypeople.data.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "recepients",
    foreignKeys = [ForeignKey
        (entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("userId"))
    ]
)
data class Recepient (
    @PrimaryKey @ColumnInfo(name = "recepientId", index = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,

    @ColumnInfo(name = "userId") val user: Int
): Serializable
