package com.teambloodformypeople

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.User
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
public class Dao_UserDaoTest {
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


    // INSERT USER
    @Test
    fun insertUserTest() = runBlockingTest {
        var user = User(
            userId = 0, email = "userName@gmail.com",password = "password",role = "Donor"
        )
        db.userDao().insertUser(user)
        val loaded = db.userDao().getUserById(user.userId!!)

        // THEN - The loaded data contains the expected values
        var userLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(userLoaded.email, user.email)
    }

    // GET USER
    @Test
    fun getUserTest() {
        var user = User(
            userId = 0, email = "userName@gmail.com",password = "password",role = "Donor"
        )
        db.userDao().insertUser(user)
        val loaded = db.userDao().getUserById(user.userId!!)

        // THEN - The loaded data contains the expected values
        var userLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(userLoaded.email, user.email)
    }

    //DELETE USER
    @Test
    fun deleteUserTest() {
        var user = User(
            userId = 0, email = "userName@gmail.com",password = "password",role = "Donor"
        )
        db.userDao().insertUser(user)
        var loaded = db.userDao().getUserById(user.userId!!)

        // THEN - The loaded data contains the expected values
        var userLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(userLoaded.email, user.email)
        db.userDao().deleteUser(userLoaded)

        loaded = db.userDao().getUserById(user.userId!!)
        assertNotEquals(userLoaded,loaded)
    }

    //UPDATE USER
    @Test
    fun updateUserTest() {
        var user = User(
            userId = 0, email = "userName@gmail.com",password = "password",role = "Donor"
        )
        db.userDao().insertUser(user)
        var loaded = db.userDao().getUserById(user.userId!!)

        // THEN - The loaded data contains the expected values
        var userLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(userLoaded.email, user.email)

        user.email = "newName"
        db.userDao().updateUser(user)

        loaded = db.userDao().getUserById(user.userId!!)
        userLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(userLoaded.email,user.email)
    }




}