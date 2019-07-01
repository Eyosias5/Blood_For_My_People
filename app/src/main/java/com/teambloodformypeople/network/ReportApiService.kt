package com.teambloodformypeople.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.util.Constants
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ReportApiService {
    //Reports
    @GET("reports")
    fun findReports(): Deferred<Response<List<Report>>>

    @GET("reports/ByDonor/{donorId}")
    fun findReportsByDonorId(@Path("donorId") donorId: Int): Deferred<Response<List<Report>>>
    @GET("reports/ByRecepient/{recepientId}")
    fun findReportsByRecepientId(@Path("recepientId") recepientId : Int): Deferred<Response<List<Report>>>

    @GET("reports/{id}")
    fun findByReportIdAsync(@Path("id") id: Int): Deferred<Response<Report>>
    @GET("reports/ByDonationHistory/{donationHistoryId}")
    fun findByDonationHistoryIdAsync(@Path("donationHistoryId") donationHistoryId: Int): Deferred<Response<Report>>

    @POST("reports")
    fun insertReportAsync(@Body newReport: Report): Deferred<Response<Void>>
    @PUT("reports/{id}")
    fun updateReportAsync(@Path("id") id: Int, @Body newReport: Report): Deferred<Response<Void>>
    @DELETE("reports/{id}")
    fun deleteReportAsync(@Path("id") id: Int): Deferred<Response<Void>>
    companion object {

        private val baseUrl = Constants().baseUrl

        fun getInstance(): ReportApiService {

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

            return retrofit.create(ReportApiService::class.java)
        }
    }
}