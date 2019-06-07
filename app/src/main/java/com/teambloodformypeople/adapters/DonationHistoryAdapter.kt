package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.models.Donation


class DonationHistoryAdapter(val context: Context): RecyclerView.Adapter<DonationHistoryAdapter.DonationHistoryViewHolder>(){

    private val donations = listOf(
        Donation(1,"June 12 20",200.0f,"0 +",23,11),
        Donation(2,"June 2 2019", 200.0F,"B -",23,11),
        Donation(3,"June 1 2019", 200.0F,"A ",23,11)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    class DonationHistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}