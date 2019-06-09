package com.teambloodformypeople.hospital
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.hospital_login.*
import kotlinx.coroutines.Dispatchers

class Register : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var registerButton: Button
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText

    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_register)
        loginButton = logout
        registerButton = register
        usernameEditText = email
        passwordEditText = password
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        registerButton.setOnClickListener {
            var username = usernameEditText.text.toString()
            var password = passwordEditText.text.toString()
            val user = User(username,password,"donor")
            if(connected()) {
                userViewModel.insertUser(user)
                userViewModel.insertResponse.observe(this, Observer {response->
                    response.body()?.run{
                        with(Dispatchers.Main){
                            Toast.makeText(this@Register, "Successfully Registered",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
        loginButton.setOnClickListener {
            login()
        }
    }
    private fun login(){
        var intent =  Intent(applicationContext,Login::class.java)
        startActivity(intent)
        finish()

    }
    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }

}
