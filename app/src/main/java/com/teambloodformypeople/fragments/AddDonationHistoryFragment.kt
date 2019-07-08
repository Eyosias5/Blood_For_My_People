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
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import kotlinx.android.synthetic.main.dialog_donor_donate.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddDonationHistoryFragment : Fragment(){
    private lateinit var viewModel: DonorViewModel
    private lateinit var recepientViewModel: RecepientViewModel
    private lateinit var donationHistoryViewModel : DonationHistoryViewModel

    private lateinit var donationHistory: DonationHistory

    val amount = MutableLiveData("")

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        donationHistoryViewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
        var view = inflater.inflate(R.layout.dialog_donor_donate,container,false)
        var args = arguments
        var currentDonorId = args?.getInt("Donor")
        var currentRecepientId = view.context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE).getInt(Constants().currentUser,-10)

        donationHistory = DonationHistory(
            donationHistoryId = 0,
            date = "",
            amount = 0.00f,
            donorId = 0,
            recepientId = 0
        )
        donationHistory.donorId = currentDonorId!!
        donationHistory.recepientId = currentRecepientId
        if(currentRecepientId!=-10){
            viewModel.getDonorById(currentDonorId)
            viewModel.getResponse.observe(this, Observer {
                with(Dispatchers.IO){
                    view.donorName.text = it.body()?.fullName
                }
            })
            recepientViewModel.getRecepientById(currentRecepientId)
            recepientViewModel.getResponse.observe(this, Observer{
                with(Dispatchers.IO){
                    view.recepientName.text = it.body()?.name
                }
            })

            with(Dispatchers.IO){
                var date = java.util.Date()
                view.date.text = """${date.day} - ${date.month} - ${date.year+1900}"""
            }
        }

        view.AddDonationHistoryBtn.setOnClickListener {
            donationHistory.amount = view.editText.text.toString().toFloat()
            donationHistoryViewModel.insertDonationHistory(donationHistory)
            Toast.makeText(view.context, "Donation History has been added", Toast.LENGTH_SHORT).show()
        }
        return view
    }


    fun insertDonationHistory(view: View){
        GlobalScope.launch {
            donationHistory.amount = amount.value!!.toFloat()
            donationHistoryViewModel.insertDonationHistory(donationHistory)
            Toast.makeText(view.context, "Donation History is added", Toast.LENGTH_SHORT).show()
        }
    }
}