package com.teambloodformypeople.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.teambloodformypeople.data.models.Report

@Dao
interface ReportDao {

    @Query("SELECT * FROM reports Where reportId = :id")
    fun getReportById(id: Int): LiveData<Report>

    @Query("SELECT * FROM reports")
    fun getReports():LiveData<List<Report>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReport(report: Report)

    @Update
    fun updateReport(report: Report)

    @Delete
    fun deleteReport(report: Report)

    @Query("DELETE FROM reports")
    fun deleteAll()

}