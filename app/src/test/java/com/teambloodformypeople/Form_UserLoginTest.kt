package com.teambloodformypeople

import android.view.View
import com.teambloodformypeople.viewmodels.UserViewModel
import org.junit.Test
import org.mockito.Mock

class Form_UserLoginTest {
    @Mock
    private lateinit var userViewModel: UserViewModel
    @Mock
    private lateinit var view: View

    @Test
    fun onValidUserLogin(){
        userViewModel.email.postValue("abc@gmail.com")
        userViewModel.password.postValue("123456opiuUUU")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e: Exception){
            assert(false)
        }
    }
    @Test
    fun onInvalidUserLogin_emptyPassword(){
        userViewModel.email.postValue("abc@gmail.com")
        userViewModel.password.postValue("")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e: Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidUserLogin_nullPassword(){
        userViewModel.email.postValue("abc@gmail.com")
        userViewModel.password.postValue(null)
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e: Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidUserLogin_emptyEmail(){
        userViewModel.email.postValue("")
        userViewModel.password.postValue("123456opiuUUU")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e: Exception){
            assert(false)
        }
    }
    @Test
    fun onInValidUserLogin_nullEmail(){
        userViewModel.email.postValue(null)
        userViewModel.password.postValue("123456opiuUUU")
        try{
            userViewModel.onLogin(view)
            assert(true)
        }
        catch (e: Exception){
            assert(false)
        }
    }
}