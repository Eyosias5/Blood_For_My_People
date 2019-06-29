package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.ReportViewModel
import kotlinx.android.synthetic.main.dialog_donor_report.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DialogDonorReportFragment : Fragment(){
    private lateinit var viewModel: DonorViewModel
    private lateinit var donationHistoryViewModel : DonationHistoryViewModel
    private lateinit var reportViewModel: ReportViewModel
    private lateinit var donationHistory: DonationHistory
    private lateinit var report: Report

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        donationHistoryViewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        var view = inflater.inflate(R.layout.dialog_donor_report,container,false)
        var args = arguments
        var currentDonationHistoryId = args?.getInt("DonationHistory")
        report = Report(
            reportId = 0,
            donationHistoryId = currentDonationHistoryId!!,
            bloodType = ""
        )
        var DonorId = MutableLiveData(0)
        var RecepientId = MutableLiveData(0)
        donationHistoryViewModel.getDonationHistoryById(currentDonationHistoryId)
        donationHistoryViewModel.getResponse.observe(this, Observer { donationHistoryResponse ->
            with(Dispatchers.IO){
                view.date.text = donationHistoryResponse.body()?.date
                view.amount.text = donationHistoryResponse.body()?.amount.toString()
                DonorId.postValue(donationHistoryResponse.body()?.donorId)
                RecepientId.postValue(donationHistoryResponse.body()?.recepientId)
            }
        })
        view.AddReportBtn.setOnClickListener {
            report.bloodType = view.bloodTypeEditText.text.toString()
            reportViewModel.insertReport(report)
            Toast.makeText(view.context, "Report is added", Toast.LENGTH_SHORT).show()
        }

        return view
    }


    fun insertReport(view: View){
        GlobalScope.launch {
            report.bloodType = view.bloodTypeEditText.text.toString()
            reportViewModel.insertReport(report)
            Toast.makeText(view.context, "Report is added", Toast.LENGTH_SHORT).show()
        }
    }
}