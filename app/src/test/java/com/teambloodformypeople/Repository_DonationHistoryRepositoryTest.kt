package com.teambloodformypeople

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.DonationHistoryDao
import com.teambloodformypeople.data.models.DonationHistory
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class Repository_DonationHistoryRepositoryTest {

    private lateinit var donationHistoryDao: DonationHistoryDao
    private lateinit var db: DB
//    private lateinit var donationHistoryRepository: DonationHistoryRepository

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DB::class.java).allowMainThreadQueries().build()
        donationHistoryDao = db.donationHistoryDao()

    }
    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }

        Handler(Looper.getMainLooper()).post {
            observeForever(observer)
        }

        latch.await(2, TimeUnit.SECONDS)
        return value
    }

    // INSERT DONATION HISTORY
    @Test
    fun insertDonationHistoryTest() {
        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
        GlobalScope.launch {
            donationHistoryDao.insertDonationHistory(donationHistory)
            var donationHistoryFetched = donationHistoryDao.getDonationHistoryById(donationHistory.donationHistoryId).blockingObserve()
            assertEquals(donationHistory,donationHistoryFetched)
        }
    }

//    // GET DONATION HISTORY
//    @Test
//    fun getDonationHistoryTest() {
//        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
//        GlobalScope.launch {
//            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
//            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
//            if (donationHistoryFetched==null){
//                assert(false)
//            }
//            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
//                assert(true)
//            }
//            assert(false)
//        }
//    }
//
//    // DELETE DONATION HISTORY
//    @Test
//    fun deleteDonationHistoryTest() {
//        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
//        GlobalScope.launch {
//            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
//            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
//            if (donationHistoryFetched==null){
//                assert(false)
//            }
//            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
//                donationHistoryRepository.deleteDonationHistoryAsync(donationHistory.donationHistoryId)
//                assert(true)
//            }
//            assert(false)
//        }
//    }
//
//    // UPDATE DONATION HISTORY
//    @Test
//    fun updateDonationHistoryTest() {
//        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
//        GlobalScope.launch {
//            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
//            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
//            if (donationHistoryFetched==null){
//                assert(false)
//            }
//            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
//                donationHistory.amount=1f
//                donationHistoryRepository.updateDonationHistoryAsync(donationHistory)
//                assert(true)
//            }
//            assert(false)
//        }
//    }
//

}