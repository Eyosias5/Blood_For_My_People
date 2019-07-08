package com.teambloodformypeople.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.Donor
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.DonorApiService
import com.teambloodformypeople.network.RecepientApiService
import com.teambloodformypeople.network.UserApiService
import com.teambloodformypeople.repositories.DonorRepository
import com.teambloodformypeople.repositories.RecepientRepository
import com.teambloodformypeople.repositories.UserRepository
import com.teambloodformypeople.util.Constants
import com.teambloodformypeople.util.TemporaryDonorHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application){
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val fullName = MutableLiveData("")
    val dateOfBirth = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val role1 = MutableLiveData("")
    val fullName1 = MutableLiveData("")
    val phoneNumber1 = MutableLiveData("")
    lateinit var sharedPreferences: SharedPreferences


    var  _context:Context
    private val userRepository: UserRepository
    private val donorRepository: DonorRepository
    private val recepientRepository : RecepientRepository

    init {
        val userApiService =  UserApiService.getInstance()
        val donorApiService = DonorApiService.getInstance()
        val recepientApiService = RecepientApiService.getInstance()
        userRepository = UserRepository(userApiService)
        donorRepository = DonorRepository(donorApiService)
        recepientRepository = RecepientRepository(recepientApiService)

        _context=application
        role1.value="Donor"
        fullName1.value="donator"
        phoneNumber1.value="0911334455"
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
    fun insertUser(user: TemporaryDonorHolder)  =viewModelScope.launch{
        _insertResponse.postValue(userRepository.insertUserAsync(user))
    }
    fun updateUser(user: User)  =viewModelScope.launch{
        _updateResponse.postValue(userRepository.updateUserAsync(user))
    }
    fun deleteUser(userId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(userRepository.deleteUserAsync(userId))
    }

    fun onLogin(view: View) {
        viewModelScope.launch{
            try{
                val response: Response<User> = userRepository.findUserByEmailAndPasswordAsync(email.value.toString(), password.value.toString())
                val user: User? = response.body()
                if (user != null && user.password.equals(password.value)) {
                    withContext(Dispatchers.Main) {
                        val sharedPreferences = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()) {
                            putString(Constants().currentRole, user.role!!)
                            apply()
                        }
                        when {
                            user.role.equals("Donor") ->
                            {
                                val donor: Donor? = donorRepository.findDonorByUserIdAsync(user.userId!!).body()
                                if(donor!=null){
                                    with(sharedPreferences.edit()){
                                        putInt(Constants().currentUser, donor.donorId)
                                        apply()
                                    }
                                    role1.value=user.role
                                    fullName1.value=donor.fullName
                                    phoneNumber1.value=donor.phoneNumber
                                    Navigation.findNavController(view).navigate(R.id.recepientListFragment)
                                }
                            }
                            user.role.equals("Recepient") ->
                            {
                                val recepient: Recepient? = recepientRepository.findRecepientByUserIdAsync(user.userId!!).body()
                                if (recepient != null) {
                                    with(sharedPreferences.edit()) {
                                        putInt(Constants().currentUser, recepient.recepientId)
                                        apply()
                                    }
                                    Navigation.findNavController(view).navigate(R.id.recepient_home_des)
                                }
                            }
                            user.role.equals("Admin") ->
                                Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.admin_action)
                        }
                    }
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(_context, "Incorrect Username/Password", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            catch(exception:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(_context, "Failed To Connect", Toast.LENGTH_SHORT).show()
                }

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
        //view.progressBar.visibility=View.VISIBLE
        viewModelScope.launch {
            try {
                val response: Response<User> = userRepository.findUserByEmailAndPasswordAsync(email.value.toString(), password.value.toString())
                val user: User? = response.body()
                if (user != null ) {
                    Toast.makeText(_context,"User already exists. Try a different Username/Password Combination.",Toast.LENGTH_SHORT).show()
                }
                else {
                    val user2 = TemporaryDonorHolder(
                        email.value.toString(),
                        password.value.toString(),
                        fullName.value.toString(),
                        dateOfBirth.value.toString(),
                        phoneNumber.value.toString()
                    )
                    withContext(Dispatchers.Main) {
                        if(donorRepository.insertDonorAsync(user2).isSuccessful){
//                        view.progressBar.visibility=View.INVISIBLE

                            Toast.makeText(_context,"Successfully Registered !",Toast.LENGTH_SHORT).show()
                            Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.alreadyMemberAction)
                        }
                        else{
                            Toast.makeText(_context,"Failed to Register!",Toast.LENGTH_SHORT).show()
                            //   view.progressBar.visibility=View.INVISIBLE
                        }

                    }
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(_context, "Failed To Connect", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun updateProfile(view: View){

        val args = Bundle()
        sharedPreferences = _context.getSharedPreferences(Constants().currentUser, Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt(Constants().currentUser, 2)
        args.putInt("user", userId)
        Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.action_profile_des_to_userUpdateFragment,args)
    }
}