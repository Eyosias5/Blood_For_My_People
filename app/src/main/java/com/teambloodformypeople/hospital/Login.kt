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
import com.teambloodformypeople.Constants
import com.teambloodformypeople.MainActivity
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.viewmodels.DonorViewModel
import com.teambloodformypeople.viewmodels.RecepientViewModel
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.hospital_login.*
import kotlinx.coroutines.Dispatchers

class Login : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var registerButton: Button
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText

    private lateinit var userViewModel: UserViewModel
    private lateinit var donorViewModel: DonorViewModel
    private lateinit var recepientViewModel: RecepientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_login)
        loginButton = logout
        registerButton = register
        usernameEditText = email
        passwordEditText = password
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        donorViewModel = ViewModelProviders.of(this).get(DonorViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)

        loginButton.setOnClickListener {
            var username = usernameEditText.text.toString()
            var password = passwordEditText.text.toString()
            if(connected()) {
                userViewModel.getUserByEmailAndPassword(username,password)
                userViewModel.getResponse.observe(this, Observer {response->
                    response.body()?.run{
                        when {
                            this.role=="Recepient" -> {
//                                getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE).edit().putInt(Constants().currentRecepient,this?.id!!).apply()
                                val intent =  Intent(applicationContext,DonorList::class.java)
                                startActivity(intent)
                                finish()
                            }
                            this.role=="Donor" -> {
//                                getSharedPreferences(Constants().currentUser,Context.MODE_PRIVATE).edit().putInt(Constants().currentDonor,this?.id!!).apply()
                                val intent =  Intent(applicationContext,DonorList::class.java)
                                startActivity(intent)
                                finish()
                            }
                            this.role=="Admin" -> {
                                val intent =  Intent(applicationContext,AddRecepient::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else ->
                                with(Dispatchers.Main){
                                Toast.makeText(this@Login,"Username and Password combination is not correct.",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
        registerButton.setOnClickListener {
            register()
        }
    }
    private fun register(){
//        var intent =  Intent(applicationContext,Register::class.java)
//        startActivity(intent)
//        finish()

    }
    private fun connected():Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }

}
