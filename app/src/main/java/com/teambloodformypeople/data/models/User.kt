package com.teambloodformypeople.data.models

import androidx.annotation.Nullable
import androidx.room.*
import java.io.Serializable

@Entity(
    tableName = "users"
)
data class User(
    @PrimaryKey @ColumnInfo(name = "userId", index = true) var id: Int,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "role") var role: String
): Serializable{
    constructor(email:String, password: String, role: String): this(0,email,password,role)
}

