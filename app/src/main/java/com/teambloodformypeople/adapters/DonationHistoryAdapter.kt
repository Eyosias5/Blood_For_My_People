package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.teambloodformypeople.data.models.DonationHistory
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import kotlinx.android.synthetic.main.donation_history_fragment.*
import kotlinx.android.synthetic.main.donation_history_item.view.*
import kotlinx.android.synthetic.main.home_item.view.*
import kotlinx.android.synthetic.main.home_item.view.donation_recpient_location_textview


class DonationHistoryAdapter(val context: Context): RecyclerView.Adapter<DonationHistoryAdapter.DonationHistoryViewHolder>(){

    private var donationHistories :List<DonationHistory> = emptyList()
    private var viewModel: DonationHistoryViewModel? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonationHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.donation_history_item, parent, false)
        return DonationHistoryViewHolder(recyclerViewItem)

    }

    override fun getItemCount(): Int {
        return donationHistories.size
    }

    override fun onBindViewHolder(holder: DonationHistoryAdapter.DonationHistoryViewHolder, position: Int) {
        val donoation= donationHistories[position]

        holder.itemView.donation_date_textview.text=donoation.date
        holder.itemView.donation_recpient_location_textview.text = donoation.amount.toString()

        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(com.teambloodformypeople.R.id.history_detail_des)
        }
    }



    class DonationHistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    internal fun setDonationHistories(donationHistories :List<DonationHistory>){
        this.donationHistories=donationHistories
        notifyDataSetChanged()
    }
}