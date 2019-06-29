package com.teambloodformypeople.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "users"
)
data class User(
    @PrimaryKey @ColumnInfo(name = "userId", index = true) var userId: Int?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "role") var role: String?
): Serializable{
    constructor(email:String, password: String, role: String): this(0,email,password,role)
}

