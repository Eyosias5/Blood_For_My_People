package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class UserFragment : Fragment(){


override fun onCreateView(inflater: LayoutInflater,
                          container: ViewGroup?,
                          savedInstanceState: Bundle?
): View? {
    setHasOptionsMenu(true)
    val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
    bottom_nav?.visibility = View.VISIBLE
    return inflater.inflate(R.layout.user_fragment,container,false)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.signOutButton)?.setOnClickListener(

            //Log out
            Navigation.createNavigateOnClickListener(R.id.signOutAction, null)

        )




    }
    companion object{
        fun newInstance(): UserFragment{
            return newInstance()
        }
    }
}