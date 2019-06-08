package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.Donation
import kotlinx.android.synthetic.main.donation_history_item.view.*


class DonationHistoryAdapter(val context: Context): RecyclerView.Adapter<DonationHistoryAdapter.DonationHistoryViewHolder>(){

    private val donations = listOf(
        Donation(1,"June 12 20",200.0f,"0 +",23,11)
    )
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonationHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.donation_history_item, parent, false)
        return DonationHistoryViewHolder(recyclerViewItem)    }

    override fun getItemCount(): Int {
        return donations.size
    }

    override fun onBindViewHolder(holder:DonationHistoryViewHolder, position: Int) {
        val donation = donations[position]

        holder.itemView.donation_date_textview.text = donation.date
        holder.itemView.donation_recpient_location_textview.text = donation.recipient.toString()



        holder.itemView.setOnClickListener{
            System.out.println("I got clicked now ")
            Navigation.createNavigateOnClickListener(R.id.history_detail_des, null)
        }
    }



    class DonationHistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}