package com.teambloodformypeople.network

import com.teambloodformypeople.data.models.DonationHistory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.teambloodformypeople.util.Constants
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface DonationHistoryApiService {
    @GET("donationHistories")
    fun findDonationHistories(): Deferred<Response<List<DonationHistory>>>

    @GET("donationHistories/byDonor/{donorId}")
    fun findDonationHistoriesByDonorId(@Path("donorId") donorId:Int): Deferred<Response<List<DonationHistory>>>

    @GET("donationHistories/{recepientId}")
    fun findDonationHistoriesByRecepientId(@Path("recepientId") recepientId: Int): Deferred<Response<List<DonationHistory>>>

    @GET("donationHistories/{id}")
    fun findByDonationHistoryIdAsync(@Path("id") id: Int): Deferred<Response<DonationHistory>>

    @POST("donationHistories")
    fun insertDonationHistoryAsync(@Body newDonationHistory: DonationHistory): Deferred<Response<Void>>

    @PUT("donationHistories/{id}")
    fun updateDonationHistoryAsnc(@Path("id") id: Int, @Body newDonationHistory: DonationHistory): Deferred<Response<Void>>

    @DELETE("donationHistories/{id}")
    fun deleteDonationHistoryAsync(@Path("id") id: Int): Deferred<Response<Void>>


    companion object {
        private val baseUrl = Constants().baseUrl

        fun getInstance(): DonationHistoryApiService {

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

            return retrofit.create(DonationHistoryApiService::class.java)
        }
    }
}