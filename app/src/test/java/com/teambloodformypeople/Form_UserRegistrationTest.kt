package com.teambloodformypeople

import android.view.View
import com.teambloodformypeople.viewmodels.UserViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class Form_UserRegistrationTest {
    @Mock
    private lateinit var userViewModel: UserViewModel
    @Mock
    private lateinit var view: View

    @Test
    fun onValidUserRegistration(){
        userViewModel.email.postValue("abc@gmail.com")
        userViewModel.password.postValue("123456opiuUUU")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInvalidUserRegistration_emptyPassword(){
        userViewModel.email.postValue("abc@gmail.com")
        userViewModel.password.postValue("")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidUserRegistration_nullPassword(){
        userViewModel.email.postValue("abc@gmail.com")
        userViewModel.password.postValue(null)
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidUserRegistration_emptyEmail(){
        userViewModel.email.postValue("")
        userViewModel.password.postValue("123456opiuUUU")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidUserRegistration_nullEmail(){
        userViewModel.email.postValue(null)
        userViewModel.password.postValue("123456opiuUUU")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e:Exception){
            assert(false)
        }
    }
}