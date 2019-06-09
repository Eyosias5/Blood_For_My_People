package com.teambloodformypeople.hospital.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Donor
import kotlinx.android.synthetic.main.hospital_donor_list_custom_view.view.*

class DonorListAdapter (val donors: List<Donor>)
    : RecyclerView.Adapter<DonorListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : DonorListAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.hospital_donor_list_custom_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: DonorListAdapter.ViewHolder, position: Int) {
        holder.fullName.text = donors[position].fullName
        holder.phoneNumber.text = donors[position].phoneNumber
    }

    override fun getItemCount(): Int {
        return donors.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val fullName: TextView = itemView.tvFullName
        val phoneNumber: TextView = itemView.tvPhoneNumber
    }
}