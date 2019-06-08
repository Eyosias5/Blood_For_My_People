package com.teambloodformypeople.repositories

import androidx.lifecycle.LiveData
import com.teambloodformypeople.data.daos.ReportDao
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.network.ReportApiService
import retrofit2.Response

class ReportRepository(reportDao: ReportDao) {
    fun findAllReports(): LiveData<Response<List<Report>>> {
        return ReportApiService.getInstance().findReports() as LiveData<Response<List<Report>>>
    }
    fun findReportById(reportId: Int): LiveData<Response<Report>> {
        return ReportApiService.getInstance().findByReportIdAsync(reportId) as LiveData<Response<Report>>
    }
    fun insertReport(report: Report) {
        ReportApiService.getInstance().insertReportAsync(report)
    }
    fun updateReport(report: Report) {
        ReportApiService.getInstance().updateReportAsync(report.id,report)
    }
    fun deleteReport(report: Report) {
        ReportApiService.getInstance().deleteReportAsync(report.id)
    }
}