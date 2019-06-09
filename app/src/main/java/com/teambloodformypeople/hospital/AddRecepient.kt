package com.teambloodformypeople.hospital
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.viewmodels.RecepientViewModel
import com.teambloodformypeople.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.hospital_add_recepient.*
import kotlinx.android.synthetic.main.hospital_login.*
import kotlinx.android.synthetic.main.hospital_login.email
import kotlinx.android.synthetic.main.hospital_login.logout
import kotlinx.android.synthetic.main.hospital_login.password
import kotlinx.android.synthetic.main.hospital_login.register
import kotlinx.coroutines.Dispatchers
import java.lang.Thread.sleep

class AddRecepient : AppCompatActivity() {
    lateinit var logoutButton: Button
    lateinit var registerButton: Button

    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var nameEditText: EditText
    lateinit var locationEditText: EditText
    lateinit var phoneNumberEditText: EditText

    private lateinit var userViewModel: UserViewModel
    private lateinit var recepientViewModel: RecepientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hospital_add_recepient)
        logoutButton = logout
        registerButton = register
        usernameEditText = email
        passwordEditText = password
        nameEditText = name
        locationEditText = location
        phoneNumberEditText = phone

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        recepientViewModel = ViewModelProviders.of(this).get(RecepientViewModel::class.java)

        registerButton.setOnClickListener {
            var username = usernameEditText.text.toString()
            var password = passwordEditText.text.toString()
            var name = nameEditText.text.toString()
            var location = locationEditText.text.toString()
            var phoneNumber = phoneNumberEditText.text.toString()

            val user = User(username,password,"recepient")
            if(connected()) {
                userViewModel.insertUser(user)
                val lifecycle = this
                userViewModel.insertResponse.observe(this, Observer { response ->
                    response.body()?.run {
                    }
                })
                userViewModel.getUserByEmailAndPassword(username, password)
                var username: MutableLiveData<Int>?=MutableLiveData(0)
                userViewModel.getResponse.observe(lifecycle, Observer { response ->
                    response.body().run {
                        username?.postValue(this?.id)
                    }
                })
                if (username != null) {
                    var recepient = Recepient(name = name, phoneNumber = phoneNumber, location = location)
                    recepient.userId = username.value!!
                    recepientViewModel.insertRecepient(recepient)
                    recepientViewModel.insertResponse.observe(lifecycle, Observer { response ->
                        response.body()?.run {
                            with(Dispatchers.Main) {
                                Toast.makeText(this@AddRecepient, "Successfully Registered", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    })
                }
            }
        }
        logoutButton.setOnClickListener {
            logout()
        }
    }
    private fun logout(){
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
