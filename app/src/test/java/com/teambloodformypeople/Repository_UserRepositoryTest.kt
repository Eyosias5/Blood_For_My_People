package com.teambloodformypeople

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.network.UserApiService
import com.teambloodformypeople.repositories.UserRepository
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
public class Repository_UserRepositoryTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var userRepository: UserRepository

    @Before
    fun before(){
        userRepository = UserRepository(UserApiService.getInstance(),DB.getDatabase(ApplicationProvider.getApplicationContext()).userDao(),Application())
    }

    // INSERT USER
    @Test
    fun insertUserTest() {
        var user = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            userRepository.insertUserAsync(user)
            var userFetched = userRepository.findUserByEmailAndPasswordAsync(user.username, user.password).body()
            if (userFetched==null){
                assert(false)
            }
            else if(userFetched.email==user.username){
                assert(true)
            }
            assert(false)
        }
    }

    // GET USER
    @Test
    fun getUserTest() {
        var user = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            userRepository.insertUserAsync(user)
            var userFetched = userRepository.findUserByEmailAndPasswordAsync(user.username, user.password).body()
            if (userFetched==null){
                assert(false)
            }
            else if(userFetched.email==user.username){
                assert(true)
            }
            assert(false)
        }
    }

    //DELETE USER
    @Test
    fun deleteUserTest() {
        var user = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            userRepository.insertUserAsync(user)
            var userFetched = userRepository.findUserByEmailAndPasswordAsync(user.username, user.password).body()
            assert(false)
            if (userFetched==null){
                assert(false)
            }
            else if(userFetched.email==user.username){
                userFetched.userId?.let { userRepository.deleteUserAsync(it) }
                assert(true)
            }
            assert(false)
        }
    }

    //UPDATE USER
    @Test
    fun updateUserTest() {
        var user = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            userRepository.insertUserAsync(user)
            var userFetched = userRepository.findUserByEmailAndPasswordAsync(user.username, user.password).body()
            if (userFetched==null){
                assert(false)
            }
            else if(userFetched.email==user.username){
                userFetched.email="b@b.com"
                userRepository.updateUserAsync(userFetched)
                assert(true)
            }
            assert(false)
        }
    }

}