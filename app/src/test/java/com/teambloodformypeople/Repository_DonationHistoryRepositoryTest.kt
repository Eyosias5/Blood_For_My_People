package com.teambloodformypeople

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.network.DonationHistoryApiService
import com.teambloodformypeople.repositories.DonationHistoryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Repository_DonationHistoryRepositoryTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var donationHistoryRepository: DonationHistoryRepository

    @Before
    fun before(){
        donationHistoryRepository = DonationHistoryRepository(DonationHistoryApiService.getInstance(),
            DB.getDatabase(ApplicationProvider.getApplicationContext()).donationHistoryDao(), Application()
        )

    }

    // INSERT DONATION HISTORY
    @Test
    fun insertDonationHistoryTest() {
        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
        GlobalScope.launch {
            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
            if (donationHistoryFetched==null){
                assert(false)
            }
            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
                assert(true)
            }
            assert(false)
        }
    }

    // GET DONATION HISTORY
    @Test
    fun getDonationHistoryTest() {
        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
        GlobalScope.launch {
            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
            if (donationHistoryFetched==null){
                assert(false)
            }
            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
                assert(true)
            }
            assert(false)
        }
    }

    // DELETE DONATION HISTORY
    @Test
    fun deleteDonationHistoryTest() {
        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
        GlobalScope.launch {
            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
            if (donationHistoryFetched==null){
                assert(false)
            }
            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
                donationHistoryRepository.deleteDonationHistoryAsync(donationHistory.donationHistoryId)
                assert(true)
            }
            assert(false)
        }
    }

    // UPDATE DONATION HISTORY
    @Test
    fun updateDonationHistoryTest() {
        var donationHistory = DonationHistory(donationHistoryId = 0, donorId = 0,recepientId = 0,amount = 0f,date = "")
        GlobalScope.launch {
            donationHistoryRepository.insertDonationHistoryAsync(donationHistory)
            var donationHistoryFetched = donationHistoryRepository.findDonationHistoryAsync(donationHistory.donationHistoryId).value
            if (donationHistoryFetched==null){
                assert(false)
            }
            else if(donationHistoryFetched.donationHistoryId==donationHistory.donationHistoryId){
                donationHistory.amount=1f
                donationHistoryRepository.updateDonationHistoryAsync(donationHistory)
                assert(true)
            }
            assert(false)
        }
    }


}