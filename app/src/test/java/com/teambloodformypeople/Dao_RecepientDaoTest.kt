package com.teambloodformypeople

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.Recepient
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
public class Dao_RecepientDaoTest {
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


    // INSERT RECEPIENT
    @Test
    fun insertRecepientTest() = runBlockingTest {
        var recepient = Recepient(
            recepientId = 0, userId = 0, name = "recepientName",phoneNumber = "0912345678",location = ""
        )
        db.recipientDao().insertRecipient(recepient)
        val loaded = db.recipientDao().getRecipientById(recepient.recepientId)

        // THEN - The loaded data contains the expected values
        var recepientLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(recepientLoaded.name, recepient.name)
    }

    // GET DONOR
    @Test
    fun getRecepientTest() {
        var recepient = Recepient(
            recepientId = 0, userId = 0, name = "recepientName",phoneNumber = "0912345678",location = ""
        )
        db.recipientDao().insertRecipient(recepient)
        val loaded = db.recipientDao().getRecipientById(recepient.recepientId)

        // THEN - The loaded data contains the expected values
        var recepientLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(recepientLoaded.name, recepient.name)
    }

    //DELETE DONOR
    @Test
    fun deleteRecepientTest() {
        var recepient = Recepient(
            recepientId = 0, userId = 0, name = "recepientName",phoneNumber = "0912345678",location = ""
        )
        db.recipientDao().insertRecipient(recepient)
        var loaded = db.recipientDao().getRecipientById(recepient.recepientId)

        // THEN - The loaded data contains the expected values
        var recepientLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(recepientLoaded.name, recepient.name)
        db.recipientDao().deleteRecipient(recepientLoaded)

        loaded = db.recipientDao().getRecipientById(recepient.recepientId)
        assertNotEquals(recepientLoaded,loaded)
    }

    //UPDATE DONOR
    @Test
    fun updateRecepientTest() {
        var recepient = Recepient(
            recepientId = 0, userId = 0, name = "recepientName",phoneNumber = "0912345678",location = ""
        )
        db.recipientDao().insertRecipient(recepient)
        var loaded = db.recipientDao().getRecipientById(recepient.recepientId)

        // THEN - The loaded data contains the expected values
        var recepientLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(recepientLoaded.name, recepient.name)

        recepient.name = "newName"
        db.recipientDao().updateRecipient(recepient)

        loaded = db.recipientDao().getRecipientById(recepient.recepientId)
        recepientLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(recepientLoaded.name,recepient.name)
    }




}