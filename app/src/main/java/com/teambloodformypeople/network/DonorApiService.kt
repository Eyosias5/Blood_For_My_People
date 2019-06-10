package com.teambloodformypeople.network

import com.teambloodformypeople.data.models.Donor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.teambloodformypeople.util.Constants
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface DonorApiService {
    //Donors
    @GET("donors")
    fun findDonors(): Deferred<Response<List<Donor>>>
    @GET("donors/{id}")
    fun findByDonorIdAsync(@Path("id") id: Int): Deferred<Response<Donor>>
    @GET("donors/byUser/{userId}")
    fun findByUserIdAsync(@Path("userId") id: Int): Deferred<Response<Donor>>
    @POST("donors")
    fun insertDonorAsync(@Body newDonor: Donor): Deferred<Response<Void>>
    @PUT("donors/{id}")
    fun updateDonorAsnc(@Path("id") id: Int, @Body newDonor: Donor): Deferred<Response<Void>>
    @DELETE("donors/{id}")
    fun deleteDonorAsync(@Path("id") id: Int): Deferred<Response<Void>>

    companion object {
        private val baseUrl = Constants().baseUrl

        fun getInstance(): DonorApiService {

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

            return retrofit.create(DonorApiService::class.java)
        }
    }
}