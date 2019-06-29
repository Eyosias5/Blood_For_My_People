package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Report
import com.teambloodformypeople.databinding.ReportItemBinding


class ReportAdapter(val context: Context): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    private var donationHistories: List<Report> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view: ReportItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.donor_item,parent,false)
        return ReportAdapter.ReportViewHolder(view)
    }

    override fun getItemCount(): Int {
        return donationHistories.size
    }

    override fun onBindViewHolder(holder: ReportAdapter.ReportViewHolder, position: Int) {
        val donors = donationHistories[position]
        holder.reportItemBinding.data = donors

    }

    class ReportViewHolder(itemView: ReportItemBinding): RecyclerView.ViewHolder(itemView.root){
        val reportItemBinding: ReportItemBinding = itemView
    }

    internal fun setDonationHistories(donationHistories:List<Report>){
        this.donationHistories=donationHistories
        notifyDataSetChanged()
    }

}