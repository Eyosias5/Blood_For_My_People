package com.teambloodformypeople.hospital.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.models.Donor
import kotlinx.android.synthetic.main.hospital_donation_history_list_custom_view.view.*
import kotlinx.android.synthetic.main.hospital_donor_list_custom_view.view.*

class DonationHistoryListAdapter (val donationHistories: List<DonationHistory>)
    : RecyclerView.Adapter<DonationHistoryListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : DonationHistoryListAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.hospital_donation_history_list_custom_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: DonationHistoryListAdapter.ViewHolder, position: Int) {
        holder.date.text = donationHistories[position].date
        holder.amount.text = donationHistories[position].amount.toString()
    }

    override fun getItemCount(): Int {
        return donationHistories.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.tvDate
        val amount: TextView = itemView.tvAmount
    }
}