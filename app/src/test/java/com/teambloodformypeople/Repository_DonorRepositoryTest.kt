package com.teambloodformypeople

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.network.DonorApiService
import com.teambloodformypeople.repositories.DonorRepository
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

public class Repository_DonorRepositoryTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var donorRepository: DonorRepository

    @Before
    fun before(){
        donorRepository = DonorRepository(DonorApiService.getInstance(),DB.getDatabase(ApplicationProvider.getApplicationContext()).donorDao(),Application())
    }

    // INSERT DONOR
    @Test
    fun insertUserTest() {
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            donorRepository.insertDonorAsync(donor)
            var donorFetched = donorRepository.findDonorByUserIdAsync(1).body()
            if (donorFetched==null){
                assert(false)
            }
            else if(donorFetched.fullName==donor.name){
                assert(true)
            }
            assert(false)
        }
    }

    // GET DONOR
    @Test
    fun getUserTest() {
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            donorRepository.insertDonorAsync(donor)
            var donorFetched = donorRepository.findDonorByUserIdAsync(1).body()
            if (donorFetched==null){
                assert(false)
            }
            else if(donorFetched.fullName==donor.name){
                assert(true)
            }
            assert(false)
        }
    }

    //DELETE DONOR
    @Test
    fun deleteUserTest() {
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            donorRepository.insertDonorAsync(donor)
            var donorFetched = donorRepository.findDonorByUserIdAsync(1).body()
            if (donorFetched==null){
                assert(false)
            }
            else if(donorFetched.fullName==donor.name){
                donorFetched.donorId.let { donorRepository.deleteDonorAsync(it) }
                assert(true)
            }
            assert(false)
        }
    }

    //UPDATE DONOR
    @Test
    fun updateUserTest() {
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        GlobalScope.launch {
            donorRepository.insertDonorAsync(donor)
            var donorFetched = donorRepository.findDonorByUserIdAsync(1).body()
            if (donorFetched==null){
                assert(false)
            }
            else if(donorFetched.fullName==donor.name){
                donorFetched.fullName="bb"
                donorRepository.updateDonorAsync(donorFetched)
                assert(true)
            }
            assert(false)
        }
    }

}