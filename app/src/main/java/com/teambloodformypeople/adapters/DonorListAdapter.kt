package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.databinding.DonorItemBinding
import com.teambloodformypeople.fragments.RecyclerViewDonorListFragmentDirections
import com.teambloodformypeople.viewmodels.DonorViewModel


class DonorListAdapter(val context: Context): RecyclerView.Adapter<DonorListAdapter.DonorViewHolder>() {

    private var donors: List<Donor> = emptyList()
    private var viewModel: DonorViewModel? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorViewHolder {
        val view: DonorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.donor_item, parent, false
        )
        return DonorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return donors.size
    }

    override fun onBindViewHolder(holder: DonorViewHolder, position: Int) {
        val donors = donors[position]
        holder.donorItemBinding.data = donors

        holder.itemView.setOnClickListener {
            var action = RecyclerViewDonorListFragmentDirections.actionRecepientHomeDesToDialogDonorDonateFragmentDes(donors.donorId)
            Navigation.findNavController(it).navigate(action)
        }

//        holder.donorItemBinding.executePendingBindings()
//        holder.donorItemBinding.donorListener = this

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
}
