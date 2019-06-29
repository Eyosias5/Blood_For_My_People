package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.databinding.DonationHistoryItemBinding
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
        val donors = donationHistories[position]
        holder.donationHistoryItemBinding.data = donors
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