package com.teambloodformypeople.viewmodels

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.UserApiService
import com.teambloodformypeople.repositories.UserRepository
import com.teambloodformypeople.util.Constants
import kotlinx.android.synthetic.main.signup_fragment.view.*
import kotlinx.coroutines.*
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application){
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val emailSignup = MutableLiveData("")
    val passwordSignUp = MutableLiveData("")

    //    val email: LiveData<String> = _email
//    val password: LiveData<String> = _password
    var  _context:Context
    private val userRepository: UserRepository

    init {
        val userApiService =  UserApiService.getInstance()
        userRepository = UserRepository(userApiService)
        _context=application
   }
    private val _getResponse = MutableLiveData<Response<User>>()
    val getResponse:LiveData<Response<User>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<User>>>()
    val getAllResponse:LiveData<Response<List<User>>>
        get() = _getAllResponse
    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse:LiveData<Response<Void>>
        get() = _insertResponse
    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse:LiveData<Response<Void>>
        get() = _deleteResponse
    private val _updateResponse = MutableLiveData<Response<Void>>()
    val updateResponse:LiveData<Response<Void>>
        get() = _updateResponse

    fun getAllUsers() =viewModelScope.launch{
        _getAllResponse.postValue(userRepository.findAllUsers())
    }
    fun getUserById(userId: Int) =viewModelScope.launch{
        _getResponse.postValue(userRepository.findUserByIdAsync(userId))
    }
    fun getUserByEmailAndPassword(email:String, password: String) =viewModelScope.launch{
        _getResponse.postValue(userRepository.findUserByEmailAndPasswordAsync(email, password))
    }
    fun insertUser(user: User)  =viewModelScope.launch{
        _insertResponse.postValue(userRepository.insertUserAsync(user))
    }
    fun updateUser(user: User)  =viewModelScope.launch{
        _updateResponse.postValue(userRepository.updateUserAsync(user))
    }
    fun deleteUser(userId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(userRepository.deleteUserAsync(userId))
    }

    fun onLogin(view: View) {
        GlobalScope.launch {
            val response: Response<User> =
                userRepository.findUserByEmailAndPasswordAsync(email.value.toString(), password.value.toString())
            val user: User? = response.body()
            if (user != null && user.password.equals(password.value)) {
                withContext(Dispatchers.Main) {
                    val sharedPreferences = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()) {
                        putInt(Constants().currentUser, user.id!!)
                        putString(Constants().currentRole, user.role!!)
                        apply()
                    }
                    when {
                        user.role.equals("Donor") ->
                            Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.login_action)
                        user.role.equals("Recepient") ->
                            Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.recepient_action)
                        user.role.equals("Admin") ->
                            Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.admin_action)

                    }
                }
            } else {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(context, "Incorrect Username/Password", Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    fun onSignUp(view:View){
        Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.gotToSignUpAction)
    }
    fun alreadyMember(view:View){
        Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.alreadyMemberAction)


    }
    fun onSignUpBtn(view:View){

        view.progressBar.visibility=View.VISIBLE
        GlobalScope.launch {
            val response: Response<User> =
                userRepository.findUserByEmailAndPasswordAsync(emailSignup.value.toString(), passwordSignUp.value.toString())
            val user: User? = response.body()
            if (user != null ) {
                Toast.makeText(_context,"User already exist",Toast.LENGTH_SHORT).show()
            }
            else {
                val user2 = User(emailSignup.value.toString(), passwordSignUp.value.toString(),"Donor")
                withContext(Dispatchers.Main) {

                    if(userRepository.insertUserAsync(user2).isSuccessful){
                        view.progressBar.visibility=View.INVISIBLE
                        Toast.makeText(_context,"successfully registered !",Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.alreadyMemberAction)

                    }
                    else{
                        Toast.makeText(_context,"failed registeration !",Toast.LENGTH_SHORT).show()
                        view.progressBar.visibility=View.INVISIBLE
                    }

                }
            }
        }
    }
}