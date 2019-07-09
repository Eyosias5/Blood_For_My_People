package com.teambloodformypeople.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recepients"
//    foreignKeys = [ForeignKey
//        (entity = User::class, parentColumns = arrayOf("userId"), childColumns = arrayOf("userId"))
//    ]
)
data class Recepient (
    @PrimaryKey @ColumnInfo(name = "recepientId", index = true) var recepientId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,

    @ColumnInfo(name = "userId") var userId: Int
): Serializable{
    constructor(name:String, location: String, phoneNumber: String): this(0,name, location, phoneNumber,0)
}
