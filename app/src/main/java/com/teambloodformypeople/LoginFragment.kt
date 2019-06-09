package com.teambloodformypeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

         val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
         bottom_nav?.visibility = View.GONE



        return inflater.inflate(R.layout.login_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.login_button)?.setOnClickListener(

            //If login successfull
            Navigation.createNavigateOnClickListener(R.id.login_action, null)

        )
        //Go to Sign up
        view.findViewById<TextView>(R.id.signUpLink)?.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.gotToSignUpAction,null)
        )
        super.onViewCreated(view, savedInstanceState)


    }
    companion object{
        fun newInstance(): LoginFragment{
            return newInstance()
        }
    }
}