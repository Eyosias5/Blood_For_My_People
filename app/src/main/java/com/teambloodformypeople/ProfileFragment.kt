package com.teambloodformypeople

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*

class ProfileFragment : Fragment(){
 lateinit var  _context:Context
lateinit var  sharedPreferences: SharedPreferences
    lateinit var nameTv:TextView
    lateinit var phoneTv:TextView
    lateinit var emailTv:TextView
    private lateinit var donorViewModel: DonorViewModel
     var currentRecepientId:Int = 0
    override fun onCreateView(inflater: LayoutInflater,
                          container: ViewGroup?,
                          savedInstanceState: Bundle?
                ): View? {
    setHasOptionsMenu(true)
    val view =inflater.inflate(R.layout.profile_fragment,container,false)
    _context=view.context
    sharedPreferences=_context.getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE)
    currentRecepientId = sharedPreferences.getInt(Constants().currentDonor,0)
    Toast.makeText(_context,currentRecepientId.toString(),Toast.LENGTH_SHORT).show()
    nameTv=view.name_tv
    phoneTv=view.phone_tv
    emailTv=view.email_tv
    updateView(currentRecepientId)
    return view
}

    private fun updateView(id:Int) {
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        donorViewModel.getDonorByUserId(id)
        donorViewModel.getResponse.observe(this, Observer {
                response -> response.body()?.run {
                nameTv.text=this.fullName
                phoneTv.text=this.phoneNumber

        }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object{
        fun newInstance(): ProfileFragment{
            return newInstance()
        }
    }
}