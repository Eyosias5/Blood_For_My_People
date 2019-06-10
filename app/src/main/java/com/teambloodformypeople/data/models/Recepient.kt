package com.teambloodformypeople.data.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "recepients",
    foreignKeys = [ForeignKey
        (entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("userId"))
    ]
)
data class Recepient (
    @PrimaryKey @ColumnInfo(name = "recepientId", index = true) var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,

    @ColumnInfo(name = "userId", index = true) var userId: Int
): Serializable{
    constructor(name:String, location: String, phoneNumber: String): this(0,name, location, phoneNumber,0)
}
