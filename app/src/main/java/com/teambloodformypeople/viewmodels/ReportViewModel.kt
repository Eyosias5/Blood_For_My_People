package com.teambloodformypeople.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.daos.ReportDao
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.repositories.ReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ReportViewModel (application: Application): AndroidViewModel(application) {
    private val reportRepository: ReportRepository
    private var reportDao: ReportDao? = null

    init {
        reportDao = DB.getDatabase(application).reportDao()
        reportRepository = ReportRepository(reportDao!!)
    }

    fun getAllReports(): LiveData<Response<List<Report>>> {
        return reportRepository.findAllReports()
    }
    fun getReportById(reportId : Int): LiveData<Response<Report>> {
        return reportRepository.findReportById(reportId)
    }
    fun insertReport(report: Report)  = viewModelScope.launch(Dispatchers.IO) {
        reportRepository.insertReport(report)
    }
    fun updateReport(report: Report)  = viewModelScope.launch(Dispatchers.IO) {
        reportRepository.updateReport(report)
    }
    fun deleteReport(report: Report)  = viewModelScope.launch(Dispatchers.IO) {
        reportRepository.deleteReport(report)
    }
}