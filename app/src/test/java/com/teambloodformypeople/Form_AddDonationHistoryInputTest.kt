//package com.teambloodformypeople
//
//import android.view.View
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.Observer
//import com.teambloodformypeople.data.models.DonationHistory
//import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
class Form_AddDonationHistoryInputTest {
//    @Mock
//    private lateinit var donationHistoryViewModel : DonationHistoryViewModel
//    @Mock
//    private lateinit var owner: LifecycleOwner
//    @Test
//    fun onValidDonationHistoryAdded(){
//        var donationHistory = DonationHistory(id = 0,amount = 0f,date = "15-05-2019",donorId = 0,recipientId = 0)
//        try {
//            donationHistoryViewModel.insertDonationHistory(donationHistory)
//            donationHistoryViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (ex:Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonationHistoryAdded_onInvalidId(){
//        var donationHistory = DonationHistory(id = -50,amount = 0f,date = "15-05-2019",donorId = 0,recipientId = 0)
//        try {
//            donationHistoryViewModel.insertDonationHistory(donationHistory)
//            donationHistoryViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (ex:Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonationHistoryAdded_onInvalidAmount(){
//        var donationHistory = DonationHistory(id = 0,amount = -50f,date = "15-05-2019",donorId = 0,recipientId = 0)
//        try {
//            donationHistoryViewModel.insertDonationHistory(donationHistory)
//            donationHistoryViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (ex:Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonationHistoryAdded_onEmptyDate(){
//        var donationHistory = DonationHistory(id = 0,amount = 0f,date = "",donorId = 0,recipientId = 0)
//        try {
//            donationHistoryViewModel.insertDonationHistory(donationHistory)
//            donationHistoryViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (ex:Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonationHistoryAdded_onInvalidDonorId(){
//        var donationHistory = DonationHistory(id = 0,amount = 0f,date = "15-05-2019",donorId = -50,recipientId = 0)
//        try {
//            donationHistoryViewModel.insertDonationHistory(donationHistory)
//            donationHistoryViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (ex:Exception){
//            assert(false)
//        }
//    }
//    @Test
//    fun onInValidDonationHistoryAdded_onInvalidRecepientId(){
//        var donationHistory = DonationHistory(id = 0,amount = 0f,date = "15-05-2019",donorId = 0,recipientId = -50)
//        try {
//            donationHistoryViewModel.insertDonationHistory(donationHistory)
//            donationHistoryViewModel.insertResponse.observe(owner, Observer {
//                    response-> response.body().run {  }
//            })
//            assert(true)
//        }
//        catch (ex:Exception){
//            assert(false)
//        }
//    }
}