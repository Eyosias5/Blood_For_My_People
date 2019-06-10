package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.databinding.DonationHistoryItemBinding
import com.teambloodformypeople.databinding.HomeItemBinding
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import kotlinx.android.synthetic.main.home_item.view.*

public class HomeAdapter(val context: Context): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    private var recipients: List<Recepient> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view: HomeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_item,parent,false)
        return HomeAdapter.HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipients.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val recipient = recipients[position]
        holder.homeItemBinding.data = recipient




    }
//    class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    class HomeViewHolder(itemView: HomeItemBinding): RecyclerView.ViewHolder(itemView.root){
        val homeItemBinding: HomeItemBinding = itemView
    }

    internal fun setRecepients(recepients:List<Recepient>){
        this.recipients=recepients
        notifyDataSetChanged()
    }

}