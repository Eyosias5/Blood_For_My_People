package com.teambloodformypeople

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.DonationHistory
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
public class Dao_DonationHistoryDaoTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = InstantTaskExecutorRule()
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: DB

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        db = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            DB::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = db.close()


    // INSERT DONATION HISTORY
    @Test
    fun insertDonationHistoryTest() = runBlockingTest {
        var donationHistory = DonationHistory(
            donationHistoryId = 0, donorId = 0, recepientId = 0, date =  "09-12-1990",amount = 2.5f
        )
        db.donationHistoryDao().insertDonationHistory(donationHistory)
        val loaded = db.donationHistoryDao().getDonationHistoryById(donationHistory.donationHistoryId)

        // THEN - The loaded data contains the expected values
        var donationHistoryLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donationHistoryLoaded.amount, donationHistory.amount)
    }

    // GET DONATION HISTORY
    @Test
    fun getDonationHistoryTest() {
        var donationHistory = DonationHistory(
            donationHistoryId = 0, donorId = 0, recepientId = 0, date =  "09-12-1990",amount = 2.5f
        )
        db.donationHistoryDao().insertDonationHistory(donationHistory)
        val loaded = db.donationHistoryDao().getDonationHistoryById(donationHistory.donationHistoryId)

        // THEN - The loaded data contains the expected values
        var donationHistoryLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donationHistoryLoaded.amount, donationHistory.amount)
    }

    //DELETE DONATION HISTORY
    @Test
    fun deleteDonationHistoryTest() {
        var donationHistory = DonationHistory(
            donationHistoryId = 0, donorId = 0, recepientId = 0, date =  "09-12-1990",amount = 2.5f
        )
        db.donationHistoryDao().insertDonationHistory(donationHistory)
        var loaded = db.donationHistoryDao().getDonationHistoryById(donationHistory.donationHistoryId)

        // THEN - The loaded data contains the expected values
        var donationHistoryLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donationHistoryLoaded.amount, donationHistory.amount)
        db.donationHistoryDao().deleteDonationHistory(donationHistoryLoaded)

        loaded = db.donationHistoryDao().getDonationHistoryById(donationHistory.donationHistoryId)
        assertNotEquals(donationHistoryLoaded,loaded)
    }

    //UPDATE DONATION HISTORY
    @Test
    fun updateDonationHistoryTest() {
        var donationHistory = DonationHistory(
            donationHistoryId = 0, donorId = 0, recepientId = 0, date =  "09-12-1990",amount = 2.5f
        )
        db.donationHistoryDao().insertDonationHistory(donationHistory)
        var loaded = db.donationHistoryDao().getDonationHistoryById(donationHistory.donationHistoryId)

        // THEN - The loaded data contains the expected values
        var donationHistoryLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donationHistoryLoaded.amount, donationHistory.amount)

        donationHistory.amount = 1.6f
        db.donationHistoryDao().updateDonationHistory(donationHistory)

        loaded = db.donationHistoryDao().getDonationHistoryById(donationHistory.donationHistoryId)
        donationHistoryLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donationHistoryLoaded.amount,donationHistory.amount)
    }




}