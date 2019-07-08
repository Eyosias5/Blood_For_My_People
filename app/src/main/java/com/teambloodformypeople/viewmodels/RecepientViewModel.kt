package com.teambloodformypeople.viewmodels

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.teambloodformypeople.data.models.Recepient
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.network.RecepientApiService
import com.teambloodformypeople.network.UserApiService
import com.teambloodformypeople.repositories.RecepientRepository
import com.teambloodformypeople.repositories.UserRepository
import com.teambloodformypeople.util.TemporaryRecepientHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RecepientViewModel(application: Application) : AndroidViewModel(application){
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val phone = MutableLiveData("")
    val location = MutableLiveData("")

    private val recepientRepository: RecepientRepository
    private val userRepository: UserRepository

    private var  _context:Context

    init {
        val recepientApiService =  RecepientApiService.getInstance()
        val userApiService = UserApiService.getInstance()
        recepientRepository = RecepientRepository(recepientApiService)
        userRepository = UserRepository(userApiService)
        _context = application
    }
    private val _getResponse = MutableLiveData<Response<Recepient>>()
    val getResponse:LiveData<Response<Recepient>>
        get() = _getResponse
    private val _getAllResponse = MutableLiveData<Response<List<Recepient>>>()
    val getAllResponse:LiveData<Response<List<Recepient>>>
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

    fun getAllRecepients() =viewModelScope.launch{
        _getAllResponse.postValue(recepientRepository.findAllRecepientsAsync())
    }
    fun getRecepientById(recepientId: Int) =viewModelScope.launch{
        _getResponse.postValue(recepientRepository.findRecepientByIdAsync(recepientId))
    }
    fun getRecepientByUserId(userId: Int) =viewModelScope.launch{
        _getResponse.postValue(recepientRepository.findRecepientByUserIdAsync(userId))
    }
    fun insertRecepient(recepient: TemporaryRecepientHolder)  =viewModelScope.launch{
        _insertResponse.postValue(recepientRepository.insertRecepientAsync(recepient))
    }
    fun updateRecepient(recepient: Recepient)  =viewModelScope.launch{
        _updateResponse.postValue(recepientRepository.updateRecepientAsync(recepient))
    }
    fun deleteRecepient(recepientId: Int)   =viewModelScope.launch{
        _deleteResponse.postValue(recepientRepository.deleteRecepientAsync(recepientId))
    }


    fun alreadyMember(view: View){
        Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.alreadyMemberAction)


    }
    fun onSignUpBtn(view: View){
//        view.progressBar.visibility= View.VISIBLE
        GlobalScope.launch {
            val response: Response<User> = userRepository.findUserByEmailAndPasswordAsync(email.value.toString(), password.value.toString())
            val user: User? = response.body()
            if (user != null ) {
                Toast.makeText(_context,"User already exists. Try a different Username/Password Combination.",Toast.LENGTH_SHORT).show()
            }
            else {
                val temporaryHolder = TemporaryRecepientHolder(
                    email.value.toString(),
                    password.value.toString(),
                     name.value.toString(),
                    location.value.toString(),
                    phone.value.toString()


                )
                withContext(Dispatchers.Main) {
                    if(recepientRepository.insertRecepientAsync(temporaryHolder).isSuccessful){
                     //   view.progressBar.visibility= View.INVISIBLE
                        Toast.makeText(_context,"Successfully Registered!", Toast.LENGTH_SHORT).show()
                        clearFields()
                       // Navigation.findNavController(view).navigate(com.teambloodformypeople.R.id.alreadyMemberAction)
                    }
                    else{
                        Toast.makeText(_context,"Failed To Register !", Toast.LENGTH_SHORT).show()
                      //  view.progressBar.visibility= View.INVISIBLE
                    }

                }
            }
        }
    }

    private fun clearFields() {
         email.value=""
         password.value=""
         name.value=""
         phone.value=""
         location.value=""
    }

}