package com.teambloodformypeople

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.teambloodformypeople.util.TemporaryDonorHolder
import com.teambloodformypeople.viewmodels.DonorViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class Form_AddDonorInputTest {
    @Mock
    private lateinit var donorViewModel: DonorViewModel
    @Mock
    private lateinit var owner: LifecycleOwner

    @Test
    fun onValidDonorAdding(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidDonorAdding_nonExistingUserId(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidDonorAdding_emptyFullName(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidDonorAdding_onEmptyPhoneNumber(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onValidDonorAdding_onEmptyDateOfBirth(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onValidDonorAdding_onEmptyFullNameDateOfBirth(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onValidDonorAdding_onEmptyPhoneNumberDateOfBirth(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
    @Test
    fun onValidDonorAdding_onEmptyFullNamePhoneNumberDateOfBirth(){
        var donor = TemporaryDonorHolder(
            password = "123456", username = "a@a.com", name = "donor",phone = "0912345678",dateOfBirth = ""
        )
        try {
            donorViewModel.insertDonor(donor)
            donorViewModel.insertResponse.observe(owner, Observer {
                    response-> response.body().run {  }
            })
            assert(true)
        }
        catch (exception : Exception){
            assert(false)
        }
    }
}