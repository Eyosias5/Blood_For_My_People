package com.teambloodformypeople.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.viewmodels.DonationHistoryViewModel
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.dialog_donor_donate.view.*
import kotlinx.android.synthetic.main.fragment_user_update.view.*
import kotlinx.coroutines.Dispatchers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UserUpdateFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val view =inflater.inflate(R.layout.fragment_user_update, container, false)
        var args = arguments
        var userId = args?.getInt("user")

         userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
         userViewModel.getUserById(userId!!)
        userViewModel.getResponse.observe(this, Observer {
            with(Dispatchers.IO){
                view.email_tv1.text= it.body()?.email
                view.password_tv.text= it.body()?.password
                view.role_tv.text= it.body()?.role
                view.email_et.setText(it.body()?.email)
                view.password_et.setText(it.body()?.password)
                view.role_et.setText(it.body()?.role)
            }
        })
        view.update_btn.setOnClickListener{
            val user =  User(userId, view.email_et.text.toString(), view.password_et.text.toString(), view.role_et.text.toString())
            if(!userViewModel.updateUser(user).isCompleted){
                Toast.makeText(view.context,"Successfully updated",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.user_list_des)
            }
            else{
                Toast.makeText(view.context,"update not successfull",Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }


}
