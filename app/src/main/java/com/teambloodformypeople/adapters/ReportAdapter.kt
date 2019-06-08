package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.Recipient
import kotlinx.android.synthetic.main.report_item.view.*

class ReportAdapter (val context: Context): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>(){

    private  val recipients = listOf(
        Recipient(12,"Brass Hospital","Bole","0912373184",2)



    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewItem = inflater.inflate(R.layout.report_item, parent, false)
        return ReportViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return recipients.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val recipient = recipients[position]

        holder.itemView.report_name_textview.text = recipient.name
        holder.itemView.report_location_textview.text = recipient.location
    }


    class ReportViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}