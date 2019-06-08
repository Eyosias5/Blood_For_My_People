package com.teambloodformypeople.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.teambloodformypeople.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users Where userId = :id")
    fun getUserById(id: Int): LiveData<User>

    @Query("SELECT * FROM users")
    fun getUsers():LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * from users ORDER BY userId")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * from users WHERE email = :email AND password = :password LIMIT 1")
    fun getUserByEmailAndPassword(email:String,password:String): LiveData<User>

}