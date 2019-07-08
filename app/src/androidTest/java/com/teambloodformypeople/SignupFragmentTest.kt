package com.teambloodformypeople

import android.os.Bundle
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.teambloodformypeople.fragments.SecuritySignupFragment
import com.teambloodformypeople.fragments.SecuritySignupFragmentDirections
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SignupFragmentTest {

    @Test
    fun testNavigationToHomeFromSignupScreen(){
        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<SecuritySignupFragment>(Bundle(),R.style.AppTheme)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!,mockNavController)
        }
        onView(withId(R.id.Signup_button)).perform(click())
//
//        verify(mockNavController).navigate(
//            SecuritySignupFragmentDirections
//        )
    }

    @Test
    fun testNavigationToLoginScreenFromSignupScreen(){
        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<SecuritySignupFragment>(Bundle(),R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!,mockNavController)
        }

        onView(withId(R.id.textview_have_account)).perform(click())

        verify(mockNavController).navigate(
            SecuritySignupFragmentDirections.alreadyMemberAction()
        )
    }
}