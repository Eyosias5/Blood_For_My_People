package com.teambloodformypeople.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface UserApiService {

    //users
    @GET("Users")
    fun findUsers(): Deferred<Response<List<User>>>
    @GET("Users/{id}")
    fun findByUserIdAsync(@Path("id") id: Int): Deferred<Response<User>>
    @GET("Users/{email}&{password}")
    fun findByUserEmailAndPasswordAsync(@Path("email") email: String,@Path("password") password: String): Deferred<Response<User>>
    @POST("Users")
    fun insertUserAsync(@Body newUser: TemporaryDonorHolder): Deferred<Response<Void>>
    @PUT("Users/{id}")
    fun updateUserAsnc(@Path("id") id: Int?, @Body newUser: User): Deferred<Response<Void>>
    @DELETE("Users/{id}")
    fun deleteUserAsync(@Path("id") id: Int): Deferred<Response<Void>>

    companion object {
        private val baseUrl = Constants().baseUrl
        fun getInstance(): UserApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(UserApiService::class.java)
        }
    }
}