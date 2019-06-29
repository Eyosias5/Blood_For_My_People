package com.teambloodformypeople.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.databinding.DonorItemBinding
import com.teambloodformypeople.listeners.DonorListener
import com.teambloodformypeople.viewmodels.DonorViewModel


class DonorListAdapter(val context: Context): RecyclerView.Adapter<DonorListAdapter.DonorViewHolder>(), DonorListener {

    private var donors: List<Donor> = emptyList()
    private var viewModel: DonorViewModel? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorViewHolder {
        val view: DonorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.donor_item, parent, false
        )
        return DonorListAdapter.DonorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return donors.size
    }

    override fun onBindViewHolder(holder: DonorListAdapter.DonorViewHolder, position: Int) {
        val donors = donors[position]
        holder.donorItemBinding.data = donors
        holder.donorItemBinding.executePendingBindings()
        holder.donorItemBinding.donorListener = this

    }

    class DonorViewHolder(itemView: DonorItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val donorItemBinding: DonorItemBinding = itemView
    }

    internal fun setDonors(donors: List<Donor>) {
        this.donors = donors
        notifyDataSetChanged()
    }
    internal fun setViewModel(viewModel: DonorViewModel){
        this.viewModel = viewModel
    }
    override fun onDonateButtonClicked(view: View, donor: Donor) {
        Toast.makeText(view.context, "${donor.phoneNumber} ${donor.fullName} ${donor.id} ${donor.userId}",Toast.LENGTH_SHORT).show()
        val args = Bundle()
        args.putInt("Donor",donor.id)
        Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.dialog_Donor_Donate_Fragment_des,args)

//        var action = DonorListDirections.actionMemberFragmentToDetailFragment()
//        action.argId = news.id
//        Navigation.findNavController(view).navigate(action)
    }
}
