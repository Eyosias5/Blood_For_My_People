package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class SignupFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {     setHasOptionsMenu(true)

        val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
        bottom_nav?.visibility = View.GONE



        return inflater.inflate(R.layout.signup_fragment,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.alreadyMemberTextView)?.setOnClickListener(

            // successfull
            Navigation.createNavigateOnClickListener(R.id.alreadyMemberAction, null)
        )
        super.onViewCreated(view, savedInstanceState)


    }

    companion object{
        fun newInstance(): SignupFragment{
            return newInstance()
        }
    }
}


