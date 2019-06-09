package com.teambloodformypeople

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teambloodformypeople.viewmodels.DonorViewModel
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.user_fragment.view.*

import androidx.navigation.Navigation

class UserFragment : Fragment() {
        lateinit var _context: Context
        lateinit var sharedPreferences: SharedPreferences
        lateinit var nameTv: TextView
        lateinit var phoneTv: TextView
        lateinit var emailTv: TextView
        private lateinit var donorViewModel: DonorViewModel
        var currentRecepientId: Int = 0
        override fun onCreateView(
            inflater: LayoutInflater,

            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            setHasOptionsMenu(true)

            val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
            bottom_nav?.visibility = View.VISIBLE


            val view = inflater.inflate(R.layout.user_fragment, container, false)
            _context = view.context
            sharedPreferences = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
            currentRecepientId = sharedPreferences.getInt(Constants().currentDonor, 0)
            Toast.makeText(_context, currentRecepientId.toString(), Toast.LENGTH_SHORT).show()
            nameTv = view.name_tv
            phoneTv = view.phone_tv
            emailTv = view.email_tv
            updateView(1)

            return view

        }

        private fun updateView(id: Int) {
            donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
            donorViewModel.getDonorByUserId(id)
            donorViewModel.getResponse.observe(this, Observer { response ->
                response.body()?.run {
                    updateView(this.fullName , this.phoneNumber)

                }
            })
        }
    private fun updateView(name: String, phone: String){
        nameTv.text =name
        phoneTv.text = phone
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<Button>(R.id.signOutButton)?.setOnClickListener(

                //Log out
                Navigation.createNavigateOnClickListener(R.id.signOutAction, null)

            )


        }

        companion object {
            fun newInstance(): UserFragment {
                return newInstance()
            }
        }
    }
