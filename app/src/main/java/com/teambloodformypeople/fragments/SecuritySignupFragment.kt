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
import com.teambloodformypeople.R
import com.teambloodformypeople.databinding.SignupFragmentBinding
import com.teambloodformypeople.viewmodels.UserViewModel

class SecuritySignupFragment : Fragment(){
    private lateinit var binding: SignupFragmentBinding
    private lateinit var viewModel:UserViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {     setHasOptionsMenu(true)

        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.GONE
        binding = DataBindingUtil.inflate(inflater, R.layout.signup_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
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


