package com.teambloodformypeople.data

import androidx.room.*
import java.io.Serializable

@Entity(
    tableName = "users"
//    ,foreignKeys = [ForeignKey
//        (entity = Role::class, parentColumns = arrayOf("role_id"), childColumns = arrayOf("user_role"))
//    ]
)
data class User (
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "user_id", index = true) val id:Int=0,
    @ColumnInfo(name = "user_email") val email: String,
    @ColumnInfo(name = "user_password") val password: String,

    @ColumnInfo(name = "user_role") val role : Int
): Serializable

