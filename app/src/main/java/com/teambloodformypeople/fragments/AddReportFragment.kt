package com.teambloodformypeople.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.teambloodformypeople.R
import com.teambloodformypeople.databinding.DialogDonorReportBinding
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import com.teambloodformypeople.viewmodels.ReportViewModel
import kotlinx.coroutines.Dispatchers

class AddReportFragment : Fragment() {
    private lateinit var binding: DialogDonorReportBinding
    private lateinit var viewModel: ReportViewModel
    private lateinit var donorViewModel: DonorViewModel
    private lateinit var recepientViewModel: RecepientViewModel
    private lateinit var donationHistoryViewModel : DonationHistoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_donor_report,container, false)
        viewModel = ViewModelProviders.of(this).get(ReportViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        donationHistoryViewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)

        val safeArgs: AddReportFragmentArgs by navArgs()
        val donationHistoryId = safeArgs.donationHistoryId


        binding.viewModel?.donationHistoryId?.value=donationHistoryId

        var report = viewModel.getReportByDonationHistoryId(donationHistoryId).observe(this, Observer{
            with(Dispatchers.IO){
                if(it!=null){
                    binding.viewModel?.bloodType?.value=(it.bloodType)
                    binding.viewModel?.reportId?.value=(it.reportId)
                    binding.AddReportBtn.text = "Update Report"
                }
                else{
                    binding.DeleteReportBtn.visibility = View.GONE
                }
            }
        })
        var ctx = this
        donationHistoryViewModel.getDonationHistoryById(donationHistoryId).observe(ctx, Observer{
            with(Dispatchers.IO){
                if(it!=null){
                    binding.amount.text = it.amount.toString()
                    binding.date.text = it.date
                    donorViewModel.getDonorById(it.donorId!!).observe(ctx, Observer {
                        it.let {
                            with(Dispatchers.IO) {
                                binding.donorName.text = it.fullName
                            }
                        }
                    })
                    recepientViewModel.getRecepientById(it.recepientId).observe(ctx, Observer {
                        it?.let {
                            with(Dispatchers.IO) {
                                binding.recepientName.text = it.name
                            }
                        }
                    })
                }
            }
        })


        var role = binding.root.context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE).getString(Constants().currentRole, "NULL")
        if (role == "Donor") {
            if(binding.viewModel!!.bloodType.value == "")
                binding.viewModel?.bloodType?.value=("Not Available Yet.")
            binding.AddReportBtn.visibility = View.GONE
            binding.DeleteReportBtn.visibility = View.GONE

            binding.bloodTypeEditText.isEnabled = false

        }
        return binding.root
    }

}