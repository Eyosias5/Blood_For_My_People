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
            GlobalScope.launch(Dispatchers.IO) {
                if(connected()) {
                    userViewModel.insertUser(readFields())
                    Log.d("MainActivity", "ADDED")
                }
            }
            clearFields()
        }

        updateButton.setOnClickListener {
            var user = readFields()
            val id:Int = searchEditText.text.toString().toInt()
            GlobalScope.launch(Dispatchers.IO) {
                if(connected()) {
                    user.id = id
                    userViewModel.updateUser(user)
                    Log.d("MainActivity", "UPDATED")
                }
            }
            clearFields()
        }

        deleteButton.setOnClickListener {
            val id: Int = searchEditText.text.toString().toInt()
            GlobalScope.launch(Dispatchers.IO) {
                if(connected()) {
                    userViewModel.deleteUser(id)
                    Log.d("MainActivity", "DELETED")
                }
            }
            clearFields()
        }

        searchButton.setOnClickListener {
            val id: Int = searchEditText.text.toString().toInt()
            GlobalScope.launch(Dispatchers.IO) {
                if(connected()) {
                    val user: User? = userViewModel.getUserById(id)
                    if(user==null){
                        Toast.makeText(this@TestingActivity,"USER WAS NOT FOUND",Toast.LENGTH_SHORT).show()
                    }
                    if (user != null) {
                        withContext(Dispatchers.Main){
                            updateFields(user)
                            Log.d("MainActivity","FOUND")
                        }
                    }
                }
            }
        }
    }

    private fun updateFields(user: User){
        user.run{
            searchEditText.setText(user.id.toString())
            emailEditText.setText(user.email)
            passwordEditText.setText(user.password)
            roleEditText.setText(user.role)
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
