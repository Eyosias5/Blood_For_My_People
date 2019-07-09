package com.teambloodformypeople

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.network.RecepientApiService
import com.teambloodformypeople.repositories.RecepientRepository
import com.teambloodformypeople.util.TemporaryRecepientHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

public class Repository_RecepientRepositoryTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var recepientRepository: RecepientRepository

    @Before
    fun before(){
        recepientRepository = RecepientRepository(RecepientApiService.getInstance(),DB.getDatabase(ApplicationProvider.getApplicationContext()).recipientDao(),Application())
    }

    // INSERT RECEPIEN
    @Test
    fun insertUserTest() {
        var recepient = TemporaryRecepientHolder(
            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",location = ""
        )
        GlobalScope.launch {
            recepientRepository.insertRecepientAsync(recepient)
            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
            if (recepientFetched==null){
                assert(false)
            }
            else if(recepientFetched.name==recepient.name){
                assert(true)
            }
            assert(false)
        }
    }

    // GET RECEPIENT
    @Test
    fun getUserTest() {
        var recepient = TemporaryRecepientHolder(
            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",location = ""
        )
        GlobalScope.launch {
            recepientRepository.insertRecepientAsync(recepient)
            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
            if (recepientFetched==null){
                assert(false)
            }
            else if(recepientFetched.name==recepient.name){
                assert(true)
            }
            assert(false)
        }
    }

    //DELETE RECEPIENT
    @Test
    fun deleteUserTest() {
        var recepient = TemporaryRecepientHolder(
            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",location = ""
        )
        GlobalScope.launch {
            recepientRepository.insertRecepientAsync(recepient)
            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
            if (recepientFetched==null){
                assert(false)
            }
            else if(recepientFetched.name==recepient.name){
                recepientFetched.recepientId.let { recepientRepository.deleteRecepientAsync(it) }
                assert(true)
            }
            assert(false)
        }
    }

    //UPDATE RECEPIENT
    @Test
    fun updateUserTest() {
        var recepient = TemporaryRecepientHolder(
            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",location = ""
        )
        GlobalScope.launch {
            recepientRepository.insertRecepientAsync(recepient)
            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
            if (recepientFetched==null){
                assert(false)
            }
            else if(recepientFetched.name==recepient.name){
                recepientFetched.name="bb"
                recepientRepository.updateRecepientAsync(recepientFetched)
                assert(true)
            }
            assert(false)
        }
    }

}