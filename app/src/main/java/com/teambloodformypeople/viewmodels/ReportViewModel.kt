package com.teambloodformypeople.viewmodels

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.network.ReportApiService
import com.teambloodformypeople.repositories.ReportRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ReportViewModel(application: Application) : AndroidViewModel(application){
    private val reportRepository: ReportRepository

    val donationHistoryId = MutableLiveData(0)
    var bloodType = MutableLiveData("")
    val reportId = MutableLiveData(0)

    var  _context: Context
    init {
        val reportApiService =  ReportApiService.getInstance()
        reportRepository = ReportRepository(reportApiService, DB.getDatabase(application).reportDao(), application)
        _context=application
    }

    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse:LiveData<Response<Void>>
        get() = _insertResponse
    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse:LiveData<Response<Void>>
        get() = _deleteResponse
    private val _updateResponse = MutableLiveData<Response<Void>>()
    val updateResponse:LiveData<Response<Void>>
        get() = _updateResponse

    fun getAllReports() =viewModelScope.launch{
        (reportRepository.findAllReportsAsync())
    }
    fun getAllReportsByDonorId(donorId:Int) =viewModelScope.launch{
        (reportRepository.findAllReportsByDonorIdAsync(donorId))
    }
    fun getAllReportsByRecepientId(recepientId:Int) =viewModelScope.launch{
       (reportRepository.findAllReportsByRecepientIdAsync(recepientId))
    }
    fun getReportById(reportId: Int) :LiveData<Report>{
        return reportRepository.findReportByIdAsync(reportId)
    }
    fun getReportByDonationHistoryId(donationHistoryId: Int) : LiveData<Report>{
        return reportRepository.findReportByDonationHistoryIdAsync(donationHistoryId)
    }
    fun insertReport(report: Report)  =viewModelScope.launch{
        _insertResponse.postValue(reportRepository.insertReportAsync(report))
    }
    fun updateReport(report: Report)  =viewModelScope.launch{
        _updateResponse.postValue(reportRepository.updateReportAsync(report))
    }
    fun deleteReport(reportId: Int)   =viewModelScope.launch{
        reportRepository.deleteReportAsync(reportId)
    }
    fun insertReport(view: View, deleteChecker: Boolean) {
        viewModelScope.launch{
            if(deleteChecker){
                if (reportId.value!=0) {
                    deleteReport(reportId=reportId.value!!)
                    Toast.makeText(view.context, "Report is deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view.context, "Report doesn't exit", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                if (reportId.value!=0) {
                    updateReport(Report(reportId=reportId.value!!,bloodType = bloodType.value!!, donationHistoryId = donationHistoryId.value!!))
                    Toast.makeText(view.context, "Report is updated", Toast.LENGTH_SHORT).show()
                } else {
                    insertReport(Report(reportId=reportId.value!!,bloodType = bloodType.value!!, donationHistoryId = donationHistoryId.value!!))
                    Toast.makeText(view.context, "Report is added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}