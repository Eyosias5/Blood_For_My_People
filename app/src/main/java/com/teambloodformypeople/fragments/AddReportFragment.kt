package com.teambloodformypeople.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import com.teambloodformypeople.viewmodels.ReportViewModel
import kotlinx.android.synthetic.main.dialog_donor_report.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddReportFragment : Fragment() {
    private lateinit var donorViewModel: DonorViewModel
    private lateinit var recepientViewModel: RecepientViewModel
    private lateinit var viewModel: ReportViewModel
    private lateinit var donationHistory: DonationHistory

    var reportId = MutableLiveData(0)

    var report = Report(
        reportId = 0,
        donationHistoryId = 0,
        bloodType = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ReportViewModel::class.java)
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
        var view = inflater.inflate(R.layout.dialog_donor_report, container, false)
        var args = arguments
        donationHistory = args?.getSerializable("DonationHistory") as DonationHistory

        view.bloodTypeEditText.setText("")

        var role = view.context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE).getString(
            Constants().currentRole, "NULL"
        )
        viewModel.getReportByDonationHistoryId(donationHistory.donationHistoryId)
        viewModel.getResponse.observe(this, Observer {
            with(Dispatchers.IO){
                if(it.body()!=null){
                    view.bloodTypeEditText.setText(it.body()?.bloodType)
                    report.reportId=it.body()?.reportId!!
                    view.AddReportBtn.text = "Update Report"
                }
            }
        })

        report.donationHistoryId = donationHistory.donationHistoryId
        with(Dispatchers.IO) {
            view.date.text = donationHistory.date
            view.amount.text = donationHistory.amount.toString()
        }
        donorViewModel.getDonorById(donationHistory.donorId)
        donorViewModel.getResponse.observe(this, Observer {
            it.let {
                with(Dispatchers.IO) {
                    view.donorName.text = it.body()?.fullName
                }
            }
        })
        recepientViewModel.getRecepientById(donationHistory.recepientId)
        recepientViewModel.getResponse.observe(this, Observer {
            it.let {
                with(Dispatchers.IO) {
                    view.recepientName.text = it.body()?.name
                }
            }
        })
        if (role == "Donor") {
            if(view.bloodTypeEditText.text.toString() == "") {
                view.bloodTypeEditText.hint = "Not Available Yet."
            }
            view.AddReportBtn.visibility = View.GONE
            view.bloodTypeEditText.isEnabled = false
        }
        else if (role == "Recepient") {
            view.AddReportBtn.setOnClickListener {
                if (report.reportId!=0) {
                    report.bloodType = view.bloodTypeEditText.text.toString()
                    viewModel.updateReport(report)
                    Toast.makeText(view.context, "Report is updated", Toast.LENGTH_SHORT).show()
                } else {
                    report.bloodType = view.bloodTypeEditText.text.toString()
                    viewModel.insertReport(report)
                    Toast.makeText(view.context, "Report is added", Toast.LENGTH_SHORT).show()
                }
            }

        }
        view.delete_report_btn.setOnClickListener{
            if (report.reportId!=0) {
                report.bloodType = view.bloodTypeEditText.text.toString()
                viewModel.deleteReport(report.reportId)
                Toast.makeText(view.context, "Report is deleted", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).popBackStack()

            }
            else{
                Toast.makeText(view.context, "Report not deleted", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }


    fun insertReport(view: View) {
        GlobalScope.launch {
            if (view.bloodTypeEditText.text.toString() != "") {
                report.bloodType = view.bloodTypeEditText.text.toString()
                report.reportId = reportId.value!!
                viewModel.updateReport(report)
                Toast.makeText(view.context, "Report is added", Toast.LENGTH_SHORT).show()
            } else {
                report.bloodType = view.bloodTypeEditText.text.toString()
                viewModel.insertReport(report)
                Toast.makeText(view.context, "Report is added", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun deleteReport(view: View) {
        GlobalScope.launch {
            if (view.bloodTypeEditText.text.toString() != "") {
                report.bloodType = view.bloodTypeEditText.text.toString()
                report.reportId = reportId.value!!
                viewModel.deleteReport(report.reportId)
                Toast.makeText(view.context, "Report is deleted", Toast.LENGTH_SHORT).show()
            }

        }
    }
}