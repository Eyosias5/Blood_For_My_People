package com.teambloodformypeople.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.UserDao
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.UserApiService
import com.teambloodformypeople.repositories.UserRepository
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Thread.sleep

class UserViewModel : ViewModel(){
    private val userRepository: UserRepository

    init {
        val userApiService =  UserApiService.getInstance()
        userRepository = UserRepository(userApiService)
   }
    private val _getResponse = MutableLiveData<Response<User>>()
    val getResponse:LiveData<Response<User>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<User>>>()
    val getAllResponse:LiveData<Response<List<User>>>
        get() = _getAllResponse
    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse:LiveData<Response<Void>>
        get() = _insertResponse
    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse:LiveData<Response<Void>>
        get() = _deleteResponse
    private val _updateResponse = MutableLiveData<Response<Void>>()
    val updateResponse:LiveData<Response<Void>>
        get() = _updateResponse

    fun getAllUsers() =viewModelScope.launch{
        _getAllResponse.postValue(userRepository.findAllUsers())
    }
    fun getUserById(userId: Int) =viewModelScope.launch{
        _getResponse.postValue(userRepository.findUserByIdAsync(userId))
    }
    fun getUserByEmailAndPassword(email:String, password: String) =viewModelScope.launch{
        _getResponse.postValue(userRepository.findUserByEmailAndPasswordAsync(email, password))
    }
    fun insertUser(user: User)  =viewModelScope.launch{
        _insertResponse.postValue(userRepository.insertUserAsync(user))
    }
    fun updateUser(user: User)  =viewModelScope.launch{
        _updateResponse.postValue(userRepository.updateUserAsync(user))
    }
    fun deleteUser(userId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(userRepository.deleteUserAsync(userId))
    }

}