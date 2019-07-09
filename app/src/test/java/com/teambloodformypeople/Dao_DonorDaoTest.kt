package com.teambloodformypeople

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.Donor
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
public class Dao_DonorDaoTest {
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


    // INSERT DONOR
    @Test
    fun insertDonorTest() = runBlockingTest {
        var donor = Donor(
            donorId = 0, userId = 0, fullName = "donorName",phoneNumber = "0912345678",dateOfBirth = ""
        )
        db.donorDao().insertDonor(donor)
        val loaded = db.donorDao().getDonorById(donor.donorId)

        // THEN - The loaded data contains the expected values
        var donorLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donorLoaded.fullName, donor.fullName)
    }

    // GET DONOR
    @Test
    fun getDonorTest() {
        var donor = Donor(
            donorId = 0, userId = 0, fullName = "donorName",phoneNumber = "0912345678",dateOfBirth = ""
        )
        db.donorDao().insertDonor(donor)
        val loaded = db.donorDao().getDonorById(donor.donorId)

        // THEN - The loaded data contains the expected values
        var donorLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donorLoaded.fullName, donor.fullName)
    }

    //DELETE DONOR
    @Test
    fun deleteDonorTest() {
        var donor = Donor(
            donorId = 0, userId = 0, fullName = "donorName",phoneNumber = "0912345678",dateOfBirth = ""
        )
        db.donorDao().insertDonor(donor)
        var loaded = db.donorDao().getDonorById(donor.donorId)

        // THEN - The loaded data contains the expected values
        var donorLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donorLoaded.fullName, donor.fullName)
        db.donorDao().deleteDonor(donorLoaded)

        loaded = db.donorDao().getDonorById(donor.donorId)
        assertNotEquals(donorLoaded,loaded)
    }

    //UPDATE DONOR
    @Test
    fun updateDonorTest() {
        var donor = Donor(
            donorId = 0, userId = 0, fullName = "donorName",phoneNumber = "0912345678",dateOfBirth = ""
        )
        db.donorDao().insertDonor(donor)
        var loaded = db.donorDao().getDonorById(donor.donorId)

        // THEN - The loaded data contains the expected values
        var donorLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donorLoaded.fullName, donor.fullName)

        donor.fullName = "newName"
        db.donorDao().updateDonor(donor)

        loaded = db.donorDao().getDonorById(donor.donorId)
        donorLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(donorLoaded.fullName,donor.fullName)
    }




}