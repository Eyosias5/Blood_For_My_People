package com.teambloodformypeople

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teambloodformypeople.util.Constants

class DonationHistoryDetailFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        var view = inflater.inflate(R.layout.donation_history_detail_fragment_donor,container,false)
        val role= context?.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)?.getString(Constants().currentRole,"null")
        if(role=="Donor"){
            return view
        }
        else if(role=="Recepient"){
            view = inflater.inflate(R.layout.donation_history_detail_fragment_recepient,container,false)
            return view
        }
        else{
            return view
        }
    }

}