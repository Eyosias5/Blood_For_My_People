package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.teambloodformypeople.data.models.DonationHistory
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import kotlinx.android.synthetic.main.donation_history_item.view.*
import kotlinx.android.synthetic.main.home_item.view.*
import kotlinx.android.synthetic.main.home_item.view.donation_recpient_location_textview


class DonationHistoryAdapter(val context: Context): RecyclerView.Adapter<DonationHistoryAdapter.DonationHistoryViewHolder>(){

    private val donations = listOf(
            DonationHistory(2,"June 11 2019",120F,1,12)
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

    override fun onBindViewHolder(holder: DonationHistoryAdapter.DonationHistoryViewHolder, position: Int) {
        val donoation= donations[position]

        holder.itemView.donation_date_textview.text=donoation.date
        holder.itemView.donation_recpient_location_textview.text = donoation.amount.toString()

        holder.itemView.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.history_detail_des,null)
        }
    }



    class DonationHistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}