package com.teambloodformypeople.repositories

import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.network.ReportApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ReportRepository(private val ReportApiService: ReportApiService) {
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
    suspend fun findReportByIdAsync(reportId: Int): Response<Report> =
        withContext(Dispatchers.IO){
            ReportApiService.findByReportIdAsync(reportId).await()
        }

    suspend fun insertReportAsync(report: Report): Response<Void> =
        withContext(Dispatchers.IO){
            ReportApiService.insertReportAsync(report).await()
        }
    suspend fun updateReportAsync(report: Report): Response<Void> =
        withContext(Dispatchers.IO){
            ReportApiService.updateReportAsync(report.reportId,report).await()
        }
    suspend fun deleteReportAsync(reportId: Int): Response<Void> =
        withContext(Dispatchers.IO){
            ReportApiService.deleteReportAsync(reportId).await()
        }
}