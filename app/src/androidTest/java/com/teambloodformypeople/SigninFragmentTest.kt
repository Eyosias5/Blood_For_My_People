package com.teambloodformypeople

import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.teambloodformypeople.fragments.SecurityLoginFragment
import com.teambloodformypeople.fragments.SecurityLoginFragmentDirections
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class SigninFragmentTest {

    @Test
    fun testNavigationToHomeScreenFromLogInScreen(){
        val mockNavController  = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<SecurityLoginFragment>(Bundle(),R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!,mockNavController)
        }

        //fails because of bypassing user
        onView(withId(R.id.login_button)).perform(click())
        verify(mockNavController).navigate(
            SecurityLoginFragmentDirections.actionLoginDesToRecepientListFragment()
        )
    }

    @Test
    fun testNavigationToSignUpScreenFromLogInScreen(){
        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<SecurityLoginFragment>(Bundle(),R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!,mockNavController)
        }

        //fails because of different arguments
        onView(withId(R.id.textview_newUser)).perform(click())
        verify(mockNavController).navigate(
            SecurityLoginFragmentDirections.gotToSignUpAction()
        )
    }
}