package com.teambloodformypeople

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.viewmodels.ReportViewModel
import org.junit.Test
import org.mockito.Mock

class Form_AddReportInputTest {
    @Mock
    private lateinit var reportViewModel: ReportViewModel
    @Mock
    private lateinit var owner: LifecycleOwner

    @Test
    fun onValidReportInput(){
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
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
    fun onInValreportIdReportInput_onInvalidId(){
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
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
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
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
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
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
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
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
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
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