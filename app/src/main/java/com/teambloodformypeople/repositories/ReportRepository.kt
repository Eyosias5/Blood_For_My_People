package com.teambloodformypeople.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.ReportDao
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.network.ReportApiService
import com.teambloodformypeople.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ReportRepository(private val ReportApiService: ReportApiService, val reportDao: ReportDao, val application: Application){
    suspend fun findAllReportsAsync(): Response<List<Report>> =
        withContext(Dispatchers.IO){
            ReportApiService.findReports().await()
        }
    suspend fun findAllReportsByDonorIdAsync(donorId : Int): Response<List<Report>> =
        withContext(Dispatchers.IO){
            ReportApiService.findReportsByDonorId(donorId).await()
        }
    suspend fun findAllReportsByRecepientIdAsync(recepientId: Int): Response<List<Report>> =
        withContext(Dispatchers.IO){
            ReportApiService.findReportsByRecepientId(recepientId).await()
        }
    fun findReportByIdAsync(reportId: Int): LiveData<Report> {
        if(Constants.connected(application )) {
            GlobalScope.launch {
               var report :Report =  ReportApiService.findByReportIdAsync(reportId).await().body()!!
                reportDao.insertReport(report)
            }
        }
        return reportDao.getReportById(reportId)
    }
    fun findReportByDonationHistoryIdAsync(donationHistoryId: Int): LiveData<Report> {
        if(Constants.connected(application )){
            GlobalScope.launch{
                var report : Report? = ReportApiService.findByDonationHistoryIdAsync(donationHistoryId).await().body()
                if(report !=null){
                    reportDao.insertReport(report)
                }

            }
        }

        return reportDao.getReportByDonationHistoryId(donationHistoryId)
    }

    suspend fun insertReportAsync(report: Report): Response<Void> =
        withContext(Dispatchers.IO){
            ReportApiService.insertReportAsync(report).await()
        }
    suspend fun updateReportAsync(report: Report): Response<Void> =
        withContext(Dispatchers.IO){
            ReportApiService.updateReportAsync(report.reportId,report).await()
        }
     fun deleteReportAsync(reportId: Int){
        GlobalScope.launch {
            reportDao.deleteById(reportId)
          ReportApiService.deleteReportAsync(reportId)
        }

    }

}