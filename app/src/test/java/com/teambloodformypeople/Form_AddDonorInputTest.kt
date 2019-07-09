//package com.teambloodformypeople
//
//import android.view.View
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.Observer
//import com.teambloodformypeople.data.models.Donor
//import com.teambloodformypeople.viewmodels.DonorViewModel
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//
//
//@RunWith(MockitoJUnitRunner::class)
class Form_AddDonorInputTest {
//    @Mock
//    private lateinit var donorViewModel: DonorViewModel
//    @Mock
//    private lateinit var owner: LifecycleOwner
//
//    @Test
//    fun onValidDonorAdding(){
//        var donor = Donor(userId = 0, fullName = "Abebe Kebede", phoneNumber = "0911121314", dateOfBirth = "19-05-2019",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonorAdding_nonExistingUserId(){
//        var donor = Donor(userId = -50, fullName = "Abebe Kebede", phoneNumber = "0911121314", dateOfBirth = "19-05-2019",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonorAdding_emptyFullName(){
//        var donor = Donor(userId = 0, fullName = "", phoneNumber = "0911121314", dateOfBirth = "19-05-2019",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonorAdding_onEmptyPhoneNumber(){
//        var donor = Donor(userId = 0, fullName = "Abebe Kebede", phoneNumber = "", dateOfBirth = "19-05-2019",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onValidDonorAdding_onEmptyDateOfBirth(){
//        var donor = Donor(userId = 0, fullName = "Abebe Kebede", phoneNumber = "0911121314", dateOfBirth = "",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onValidDonorAdding_onEmptyFullNameDateOfBirth(){
//        var donor = Donor(userId = 0, fullName = "", phoneNumber = "0911121314", dateOfBirth = "",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onValidDonorAdding_onEmptyPhoneNumberDateOfBirth(){
//        var donor = Donor(userId = 0, fullName = "Abebe Kebede", phoneNumber = "", dateOfBirth = "",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onValidDonorAdding_onEmptyFullNamePhoneNumberDateOfBirth(){
//        var donor = Donor(userId = 0, fullName = "", phoneNumber = "", dateOfBirth = "",id = 0)
//        try {
//            donorViewModel.insertDonor(donor)
//            donorViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (exception : Exception){
//            assert(false)
//        }
//    }
}