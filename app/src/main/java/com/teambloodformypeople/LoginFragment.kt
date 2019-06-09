package com.teambloodformypeople

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambloodformypeople.hospital.AddRecepient
import com.teambloodformypeople.hospital.DonorList
import com.teambloodformypeople.hospital.Login
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.coroutines.Dispatchers

class LoginFragment: Fragment(){
    lateinit var  _context:Context
    private lateinit var emailTv:EditText
    private lateinit var passTv:EditText

    private lateinit var userViewModel: UserViewModel
    private lateinit var donorViewModel: DonorViewModel
    private lateinit var recepientViewModel: RecepientViewModel
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

         val bottom_nav = this.activity?.findViewById<View>(R.id.bottom_nav_view)
         bottom_nav?.visibility = View.GONE
        val view =inflater.inflate(R.layout.login_fragment,container,false)
        _context=view.context
        emailTv= view.email_tv!!
        passTv=view.pass_tv

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.login_button)?.setOnClickListener(

            //If login successfull
            loginBtnClicked()


        )
        //Go to Sign up
        view.findViewById<TextView>(R.id.signUpLink)?.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.gotToSignUpAction,null)
        )
        super.onViewCreated(view, savedInstanceState)


    }

    private fun loginBtnClicked(): View.OnClickListener? {

         View.OnClickListener {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
            donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
            recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)
            var username = emailTv.text.toString()
            var password = passTv.text.toString()

            userViewModel.getUserByEmailAndPassword(username,password)
            userViewModel.getResponse.observe(this, Observer {response->
                response.body()?.run{
                    when {
                        this.role=="Recepient" -> {
                            recepientViewModel.getRecepientByUserId(this.id!!)
                            recepientViewModel.getResponse.observe(Login(),Observer{ response->
                                response.body()?.run {
                                    var sharedPrefs = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
                                    sharedPrefs.edit().putInt(Constants().currentRecepient,this.id).apply()
                                }
                            })
                            Toast.makeText(_context,"Recepient role",Toast.LENGTH_SHORT).show()


                        }
                        this.role=="Donor" -> {
                            donorViewModel.getDonorByUserId(this.id!!)
                            var sharedPrefs = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
                            sharedPrefs.edit().putInt(Constants().currentDonor, this.id!!).apply()

                            Navigation.createNavigateOnClickListener(R.id.login_action, null)



                        }
                        this.role=="Admin" -> {
                            Toast.makeText(_context,"Admin role",Toast.LENGTH_SHORT).show()
                        }
                        else ->
                            with(Dispatchers.Main){
                                Toast.makeText(_context,"Username and Password combination is not correct.", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            })


        }
        return Navigation.createNavigateOnClickListener(R.id.login_action, null)
    }

    companion object{
        fun newInstance(): LoginFragment{
            return newInstance()
        }
    }
}