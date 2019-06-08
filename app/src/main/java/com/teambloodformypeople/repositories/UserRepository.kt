package com.teambloodformypeople.repositories

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.teambloodformypeople.data.daos.UserDao
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.UserApiService
import kotlinx.coroutines.*
import retrofit2.Response

class UserRepository(userDao: UserDao) : LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        return this.lifecycle
    }

    fun findAllUsers(): LiveData<Response<List<User>>> {
        return UserApiService.getInstance().findUsers() as LiveData<Response<List<User>>>
    }

    fun findUserById(userId: Int): User? {
        var user:User? = null
        val d = UserApiService.getInstance().findByUserIdAsync(userId)
        GlobalScope.launch (Dispatchers.Unconfined) {
            val userResponse = d.await()
            user = userResponse.body()
        }
        return user
    }
    fun insertUserAsync(user: User): Deferred<Response<Void>> {
        return UserApiService.getInstance().insertUserAsync(user)
    }
    fun updateUserAsync(user: User):Deferred<Response<Void>> {
        return UserApiService.getInstance().updateUserAsnc(user.id,user)
    }
    fun deleteUserAsync(userId: Int):Deferred<Response<Void>> {
        return UserApiService.getInstance().deleteUserAsync(userId)
    }
}