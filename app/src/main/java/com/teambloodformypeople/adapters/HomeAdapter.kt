package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.models.Recipient
import kotlinx.android.synthetic.main.home_item.view.*

class HomeAdapter(val context: Context): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private  val recipients = listOf(
        Recipient(12,"Brass Hospital","Bole","0912373184",2),
        Recipient(12,"Brass Hospital","Bole","0912373184",2),
        Recipient(12,"Brass Hospital","Bole","0912373184",2),
        Recipient(12,"Brass Hospital","Bole","0912373184",2),
        Recipient(12,"Brass Hospital","Bole","0912373184",2)


    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.home_item, parent, false)
        return HomeViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return recipients.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val recipient = recipients[position]

        holder.itemView.recipient_name_textview.text = recipient.name
        holder.itemView.donation_recpient_location_textview.text = recipient.location

    }
    class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView);


}