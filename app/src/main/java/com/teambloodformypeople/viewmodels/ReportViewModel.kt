package com.teambloodformypeople.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.network.ReportApiService
import com.teambloodformypeople.repositories.ReportRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ReportViewModel : ViewModel(){
    private val reportRepository: ReportRepository

    init {
        val reportApiService =  ReportApiService.getInstance()
        reportRepository = ReportRepository(reportApiService)
    }
    private val _getResponse = MutableLiveData<Response<Report>>()
    val getResponse:LiveData<Response<Report>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<Report>>>()
    val getAllResponse:LiveData<Response<List<Report>>>
        get() = _getAllResponse
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
        _getAllResponse.postValue(reportRepository.findAllReportsAsync())
    }
    fun getAllReportsByDonorId(donorId:Int) =viewModelScope.launch{
        _getAllResponse.postValue(reportRepository.findAllReportsByDonorIdAsync(donorId))
    }
    fun getAllReportsByRecepientId(recepientId:Int) =viewModelScope.launch{
        _getAllResponse.postValue(reportRepository.findAllReportsByRecepientIdAsync(recepientId))
    }
    fun getReportById(reportId: Int) =viewModelScope.launch{
        _getResponse.postValue(reportRepository.findReportByIdAsync(reportId))
    }
    fun getReportByDonationHistoryId(donationHistoryId: Int) =viewModelScope.launch{
        _getResponse.postValue(reportRepository.findReportByDonationHistoryIdAsync(donationHistoryId))
    }
    fun insertReport(report: Report)  =viewModelScope.launch{
        _insertResponse.postValue(reportRepository.insertReportAsync(report))
    }
    fun updateReport(report: Report)  =viewModelScope.launch{
        _updateResponse.postValue(reportRepository.updateReportAsync(report))
    }
    fun deleteReport(reportId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(reportRepository.deleteReportAsync(reportId))
    }

}