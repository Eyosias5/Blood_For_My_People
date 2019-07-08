package com.teambloodformypeople.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_update.view.*
import kotlinx.coroutines.Dispatchers

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
         val user = userViewModel.getUserById(userId!!)
        user.observe(this, Observer {
            view.email_tv1.text= it.email
            view.password_tv.text= it.password
            view.role_tv.text= it.role
            view.email_et.setText(it.email)
            view.password_et.setText(it.password)
            view.role_et.setText(it.role)
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
