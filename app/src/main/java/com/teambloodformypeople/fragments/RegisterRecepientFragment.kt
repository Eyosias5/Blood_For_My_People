package com.teambloodformypeople.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambloodformypeople.R
import com.teambloodformypeople.databinding.FragmentAdminBinding
import com.teambloodformypeople.viewmodels.RecepientViewModel

class RegisterRecepientFragment : Fragment(){

    private lateinit var binding: FragmentAdminBinding
    private lateinit var viewModel:RecepientViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        var bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
        bottom_nav.visibility = View.VISIBLE
        bottom_nav.menu.clear()
        bottom_nav.inflateMenu(R.menu.admin_bottom_nav_menu)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin, container, false)
        viewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.alreadyMemberTextView)?.setOnClickListener(

            // successfull
            Navigation.createNavigateOnClickListener(R.id.alreadyMemberAction, null)
        )
        super.onViewCreated(view, savedInstanceState)


    }

    companion object{
        fun newInstance(): SecuritySignupFragment {
            return newInstance()
        }
    }
}


