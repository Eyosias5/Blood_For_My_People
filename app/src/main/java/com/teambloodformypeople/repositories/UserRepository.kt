package com.teambloodformypeople.repositories

import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.UserApiService
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val UserApiService: UserApiService) {

    suspend fun findAllUsers(): Response<List<User>> =
        withContext(Dispatchers.IO){
            UserApiService.findUsers().await()
    }

    suspend fun findUserByIdAsync(userId: Int): Response<User> =
        withContext(Dispatchers.IO){
            UserApiService.findByUserIdAsync(userId).await()
        }

    suspend fun findUserByEmailAndPasswordAsync(email: String, password: String): Response<User> =
        withContext(Dispatchers.IO){
            UserApiService.findByUserEmailAndPasswordAsync(email, password).await()
        }

    suspend fun insertUserAsync(user: TemporaryDonorHolder): Response<Void> =
        withContext(Dispatchers.IO){
          UserApiService.insertUserAsync(user).await()
        }
    suspend fun updateUserAsync(user: User):Response<Void> =
        withContext(Dispatchers.IO){
        UserApiService.updateUserAsnc(user.userId,user).await()
    }
    suspend fun deleteUserAsync(userId: Int):Response<Void> =
        withContext(Dispatchers.IO){
        UserApiService.deleteUserAsync(userId).await()
    }
}