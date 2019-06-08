package com.teambloodformypeople.network

import com.teambloodformypeople.data.models.Recepient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.teambloodformypeople.Constants
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface RecepientApiService {
    //Recepients
    @GET("recepients")
    fun findRecepients(): Deferred<Response<List<Recepient>>>
    @GET("recepients/{id}")
    fun findByRecepeintIdAsync(@Path("id") id: Int): Deferred<Response<Recepient>>
    @POST("recepients")
    fun insertRecepeintAsync(@Body newRecepient: Recepient): Deferred<Response<Void>>
    @PUT("recepients/{id}")
    fun updateRecepeintAsnc(@Path("id") id: Int, @Body newRecepient: Recepient): Deferred<Response<Void>>
    @DELETE("recepients/{id}")
    fun deleteRecepeintAsync(@Path("id") id: Int): Deferred<Response<Void>>

    companion object {

        private val baseUrl = Constants().baseUrl

        fun getInstance(): RecepientApiService {

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

            return retrofit.create(RecepientApiService::class.java)
        }
    }
}