package com.teambloodformypeople.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.teambloodformypeople.R
import com.teambloodformypeople.databinding.LoginFragmentBinding
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class SecurityLoginFragment: Fragment(){

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel:UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        setHasOptionsMenu(true)

        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.GONE

        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this





        return binding.root


    }



    companion object{
        fun newInstance(): SecurityLoginFragment {
            return newInstance()
        }
    }
}