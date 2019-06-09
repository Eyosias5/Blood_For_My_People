package com.teambloodformypeople.hospital.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.DonationHistory
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.data.models.Report
import kotlinx.android.synthetic.main.hospital_donation_history_list_custom_view.view.*
import kotlinx.android.synthetic.main.hospital_donor_list_custom_view.view.*
import kotlinx.android.synthetic.main.hospital_report_list_custom_view.view.*

class ReportListAdapter (val reports: List<Report>)
    : RecyclerView.Adapter<ReportListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ReportListAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.hospital_report_list_custom_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReportListAdapter.ViewHolder, position: Int) {
        holder.id.text = reports[position].donationHistoryId.toString()
        holder.bloodType.text = reports[position].bloodType
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val id: TextView = itemView.tvId
        val bloodType: TextView = itemView.tvBloodType
    }
}