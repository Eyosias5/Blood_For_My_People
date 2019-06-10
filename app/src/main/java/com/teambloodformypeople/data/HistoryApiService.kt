package et.edu.aait.roomdbexample.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface HistoryApiService {

    @GET("DonationHistories/{id}")
    fun findByIdAsync(@Path("id") id: Long): Deferred<Response<List<DonationHistory>>>
    @GET("DonationHistories/")
    fun findAll(): Deferred<Response<List<DonationHistory>>>
    @POST("courses")
    fun insertCourseAsync(@Body newCourse: DonationHistory): Deferred<Response<Void>>
    @PUT("courses/{id}")
    fun updateCourseAsnc(@Path("id") id: Long, @Body newCourse: DonationHistory): Deferred<Response<Void>>
    @DELETE("courses/{id}")
    fun deleteCourseAsync(@Path("id") id: Long): Deferred<Response<Void>>


    companion object {

        private val baseUrl = "http://192.168.43.242:5001/api/"

        fun getInstance(): HistoryApiService {

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

            return retrofit.create(HistoryApiService::class.java)
        }
    }
}





















