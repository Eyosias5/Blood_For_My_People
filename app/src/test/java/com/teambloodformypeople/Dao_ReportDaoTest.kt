package com.teambloodformypeople

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.teambloodformypeople.data.DB
import com.teambloodformypeople.data.models.Report
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
public class Dao_ReportDaoTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = InstantTaskExecutorRule()
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: DB

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        db = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            DB::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = db.close()


    // INSERT REPORT
    @Test
    fun insertReportTest() = runBlockingTest {
        var report = Report(
            reportId = 0, donationHistoryId = 0, bloodType = "AB"
        )
        db.reportDao().insertReport(report)
        val loaded = db.reportDao().getReportById(report.reportId)

        // THEN - The loaded data contains the expected values
        var reportLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(reportLoaded.bloodType, report.bloodType)
    }

    // GET REPORT
    @Test
    fun getReportTest() {
        var report = Report(
            reportId = 0, donationHistoryId = 0, bloodType = "AB"
        )
        db.reportDao().insertReport(report)
        val loaded = db.reportDao().getReportById(report.reportId)

        // THEN - The loaded data contains the expected values
        var reportLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(reportLoaded.bloodType, report.bloodType)
    }

    //DELETE REPORT
    @Test
    fun deleteReportTest() {
        var report = Report(
            reportId = 0, donationHistoryId = 0, bloodType = "AB"
        )
        db.reportDao().insertReport(report)
        var loaded = db.reportDao().getReportById(report.reportId)

        // THEN - The loaded data contains the expected values
        var reportLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(reportLoaded.bloodType, report.bloodType)
        db.reportDao().deleteReport(reportLoaded)

        loaded = db.reportDao().getReportById(report.reportId)
        assertNotEquals(reportLoaded,loaded)
    }

    //UPDATE REPORT
    @Test
    fun updateReportTest() {
        var report = Report(
            reportId = 0, donationHistoryId = 0, bloodType = "AB"
        )
        db.reportDao().insertReport(report)
        var loaded = db.reportDao().getReportById(report.reportId)

        // THEN - The loaded data contains the expected values
        var reportLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(reportLoaded.bloodType, report.bloodType)

        report.bloodType = "O"
        db.reportDao().updateReport(report)

        loaded = db.reportDao().getReportById(report.reportId)
        reportLoaded = LiveDataTestUtil.getValue(loaded)
        assertEquals(reportLoaded.bloodType,report.bloodType)
    }




}