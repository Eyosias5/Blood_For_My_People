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
import com.teambloodformypeople.databinding.DialogDonorDonateBinding
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import kotlinx.coroutines.Dispatchers

class AddDonationHistoryFragment : Fragment(){
    private lateinit var binding: DialogDonorDonateBinding
    private lateinit var viewModel: DonationHistoryViewModel
    private lateinit var recepientViewModel: RecepientViewModel
    private lateinit var donorViewModel : DonorViewModel


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_donor_donate,container, false)
        viewModel = ViewModelProviders.of(this).get(DonationHistoryViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)

        val safeArgs: AddDonationHistoryFragmentArgs by navArgs()
        val donorId = safeArgs.donorId

        binding.viewModel?.donorId?.value = donorId
        donorViewModel.getDonorById(donorId).observe(this, Observer {
            with(Dispatchers.IO){
                binding.donorName.text = it.fullName
            }
        })
        var user = binding.root.context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE).getInt(Constants().currentUser, 0)
        recepientViewModel.getRecepientById(user).observe(this, Observer{
            with(Dispatchers.IO){
                binding.recepientName.text = it.name
            }
        })
        binding.DeleteDonationHistoryBtn.visibility = View.GONE

        var role = binding.root.context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE).getString(Constants().currentRole, "NULL")
        if (role == "Donor") {
//            if(binding.viewModel!!.amount.toFloat().value == 0f)
//                binding.viewModel?.amount?.value=("")
            binding.AddDonationHistoryBtn.visibility = View.GONE
            binding.donationAmountEditText.isEnabled = false

        }
        return binding.root
    }
}