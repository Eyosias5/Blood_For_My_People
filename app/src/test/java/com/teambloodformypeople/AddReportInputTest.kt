package com.teambloodformypeople

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.viewmodels.ReportViewModel
import org.junit.Test
import org.mockito.Mock
import java.lang.Exception

class AddReportInputTest {
    @Mock
    private lateinit var reportViewModel: ReportViewModel
    @Mock
    private lateinit var owner: LifecycleOwner

    @Test
    fun onValidReportInput(){
        var report = Report(id = 0,bloodType = "A",donationHistoryId = 0)
        try{
            reportViewModel.insertReport(report)
            reportViewModel.insertResponse.observe(owner, Observer {
                    response->response.body().run {  }
            })
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidReportInput_onInvalidId(){
        var report = Report(id = -50,bloodType = "A",donationHistoryId = 0)
        try{
            reportViewModel.insertReport(report)
            reportViewModel.insertResponse.observe(owner, Observer {
                    response->response.body().run {  }
            })
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidReportInput_onEmptyBloodType(){
        var report = Report(id = 0,bloodType = "",donationHistoryId = 0)
        try{
            reportViewModel.insertReport(report)
            reportViewModel.insertResponse.observe(owner, Observer {
                    response->response.body().run {  }
            })
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidReportInput_onInvalidBloodType(){
        var report = Report(id = 0,bloodType = "Z",donationHistoryId = 0)
        try{
            reportViewModel.insertReport(report)
            reportViewModel.insertResponse.observe(owner, Observer {
                    response->response.body().run {  }
            })
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidReportInput_onInvalidDonationHistoryId(){
        var report = Report(id = 0,bloodType = "A",donationHistoryId = -50)
        try{
            reportViewModel.insertReport(report)
            reportViewModel.insertResponse.observe(owner, Observer {
                    response->response.body().run {  }
            })
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInvalidReportInput_onCombinationOfInvalidEntries(){
        var report = Report(id = -50,bloodType = "ABCD",donationHistoryId = -50)
        try{
            reportViewModel.insertReport(report)
            reportViewModel.insertResponse.observe(owner, Observer {
                    response->response.body().run {  }
            })
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
}