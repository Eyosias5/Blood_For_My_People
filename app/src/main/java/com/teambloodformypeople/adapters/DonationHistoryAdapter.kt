package com.teambloodformypeople.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.databinding.DonationHistoryItemBinding
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel


class DonationHistoryAdapter(val context: Context): RecyclerView.Adapter<DonationHistoryAdapter.DonationHistoryViewHolder>() {

    private var donationHistories: List<DonationHistory> = emptyList()
    private var viewModel: DonationHistoryViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationHistoryViewHolder {
        val view: DonationHistoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.donation_history_item,parent,false)
        return DonationHistoryAdapter.DonationHistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return donationHistories.size
    }

    override fun onBindViewHolder(holder: DonationHistoryAdapter.DonationHistoryViewHolder, position: Int) {
        val donationHistory = donationHistories[position]
        holder.donationHistoryItemBinding.data = donationHistory
        holder.itemView.setOnClickListener {
            val args = Bundle()
            args.putSerializable("DonationHistory",donationHistory)

            val sharedPreferences = it.context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
            val role = sharedPreferences.getString(Constants().currentRole,"Not Known")
            Navigation.findNavController(it).navigate(R.id.report_add_fragment,args)
        }
//        holder.donationHistoryItemBinding.executePendingBindings()

    }
    internal fun setViewModel(viewModel: DonationHistoryViewModel){
        this.viewModel = viewModel
    }
    class DonationHistoryViewHolder(itemView: DonationHistoryItemBinding): RecyclerView.ViewHolder(itemView.root){
        val donationHistoryItemBinding: DonationHistoryItemBinding = itemView
    }

    internal fun setDonationHistories(donationHistories:List<DonationHistory>){
        this.donationHistories=donationHistories
        notifyDataSetChanged()
    }

}