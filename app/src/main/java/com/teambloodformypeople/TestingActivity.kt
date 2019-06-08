package com.teambloodformypeople

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_testing.*
import kotlinx.coroutines.*
import retrofit2.Response
import java.util.*

class TestingActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var searchButton: Button

    private lateinit var roleEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var searchEditText: EditText

    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        addButton = add_button
        deleteButton = delete_button
        updateButton = update_button
        searchButton = search_button

        roleEditText = role_edit_text
        emailEditText = email_edit_text
        passwordEditText = password_edit_text
        searchEditText = search_content

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        addButton.setOnClickListener {
            val user = readFields()
            if(connected()){
                userViewModel.insertUser(user)
                userViewModel.insertResponse.observe(this,Observer{response->
                    response.body().run{

                    }
                })
            }
            clearFields()
        }

        updateButton.setOnClickListener {
            val id: Int = searchEditText.text.toString().toInt()
            var user = readFields()
            user.id = id
            if(connected()){
                userViewModel.updateUser(user)
                userViewModel.updateResponse.observe(this,Observer{response->
                    response.body().run{

                    }
                })
            }
            clearFields()
        }

        deleteButton.setOnClickListener {
            val id: Int = searchEditText.text.toString().toInt()
            if(connected()){
                userViewModel.deleteUser(id)
                userViewModel.deleteResponse.observe(this,Observer{response->
                    response.body().run{

                    }
                })
            }
            clearFields()
        }

        searchButton.setOnClickListener {
            val id: Int = searchEditText.text.toString().toInt()
            if(connected()) {
                userViewModel.getUserById(id)
                userViewModel.getResponse.observe(this, Observer {response->
                    response.body()?.run{
                        updateFields(this)
                    }
                })
            }
        }
    }

    private fun updateFields(user: User){
        user.run{
            searchEditText.setText(this.id.toString())
            emailEditText.setText(this.email)
            passwordEditText.setText(this.password)
            roleEditText.setText(this.role)
        }
    }

    private fun readFields() = User(
        emailEditText.text.toString(),
        passwordEditText.text.toString(),
        roleEditText.text.toString()
    )

    private fun clearFields() {
        searchEditText.setText("")
        emailEditText.setText("")
        passwordEditText.setText("")
        roleEditText.setText("")
    }

    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }
}
