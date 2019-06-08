package com.teambloodformypeople.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.UserDao
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val userRepository: UserRepository
    private var userDao: UserDao? = null

    init {
        userDao = DB.getDatabase(application).userDao()
        userRepository = UserRepository(userDao!!)
   }

    fun getAllUsers(): LiveData<Response<List<User>>> {
        return userRepository.findAllUsers()
    }
    fun getUserById(userId: Int): User? {
        var user:User? = null
        with(userRepository.findUserById(userId)){
            if(this!=null){
                user = User(this.id,this.email,this.password,this.role)
            }
        }
        return user
    }
    fun insertUser(user: User)  = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insertUserAsync(user).await()
    }
    fun updateUser(user: User)  = viewModelScope.launch(Dispatchers.IO) {
        userRepository.updateUserAsync(user).await()
    }
    fun deleteUser(userId: Int)  = viewModelScope.launch(Dispatchers.IO) {
        userRepository.deleteUserAsync(userId).await()
    }

}