package com.teambloodformypeople

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.network.ReportApiService
import com.teambloodformypeople.repositories.ReportRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Repository_ReportRepositoryTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var reportRepository: ReportRepository

    @Before
    fun before(){
        reportRepository = ReportRepository(ReportApiService.getInstance(),
            DB.getDatabase(ApplicationProvider.getApplicationContext()).reportDao(), Application()
        )

    }

    // INSERT REPORT
    @Test
    fun insertReportTest() {
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
        GlobalScope.launch {
            reportRepository.insertReportAsync(report)
            var reportFetched = reportRepository.findReportByIdAsync(report.reportId).value
            if (reportFetched==null){
                assert(false)
            }
            else if(reportFetched.reportId==report.reportId){
                assert(true)
            }
            assert(false)
        }
    }

    // GET REPORT
    @Test
    fun getReportTest() {
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
        GlobalScope.launch {
            reportRepository.insertReportAsync(report)
            var reportFetched = reportRepository.findReportByIdAsync(report.reportId).value
            if (reportFetched==null){
                assert(false)
            }
            else if(reportFetched.reportId==report.reportId){
                assert(true)
            }
            assert(false)
        }
    }

    // DELETE REPORT
    @Test
    fun deleteReportTest() {
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
        GlobalScope.launch {
            reportRepository.insertReportAsync(report)
            var reportFetched = reportRepository.findReportByIdAsync(report.reportId).value
            if (reportFetched==null){
                assert(false)
            }
            else if(reportFetched.reportId==report.reportId){
                reportRepository.deleteReportAsync(report.reportId)
                assert(true)
            }
            assert(false)
        }
    }

    // UPDATE REPORT
    @Test
    fun updateReportTest() {
        var report = Report(reportId = 0,donationHistoryId = 0,bloodType = "AB")
        GlobalScope.launch {
            reportRepository.insertReportAsync(report)
            var reportFetched = reportRepository.findReportByIdAsync(report.reportId).value
            if (reportFetched==null){
                assert(false)
            }
            else if(reportFetched.reportId==report.reportId){
                report.bloodType="AB"
                reportRepository.updateReportAsync(report)
                assert(true)
            }
            assert(false)
        }
    }


}