package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.databinding.RecepientItemBinding
import com.teambloodformypeople.viewmodels.RecepientViewModel

class RecepientsListAdapter(val context: Context): RecyclerView.Adapter<RecepientsListAdapter.RecepeintViewHolder>() {

    private var recipients: List<Recepient> = emptyList()
    private var viewModel: RecepientViewModel?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecepeintViewHolder {
        val view: RecepientItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recepient_item,parent,false)
        return RecepientsListAdapter.RecepeintViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipients.size
    }

    override fun onBindViewHolder(holder: RecepeintViewHolder, position: Int) {
        val recipient = recipients[position]
        holder.recepientItemBinding.data = recipient
        holder.recepientItemBinding.executePendingBindings()
    }

    class RecepeintViewHolder(itemView: RecepientItemBinding): RecyclerView.ViewHolder(itemView.root){
        val recepientItemBinding: RecepientItemBinding = itemView
    }

    internal fun setRecepients(recepients:List<Recepient>){
        this.recipients=recepients
        notifyDataSetChanged()
    }
    internal fun setViewModel(viewModel: RecepientViewModel){
        this.viewModel = viewModel
    }

}