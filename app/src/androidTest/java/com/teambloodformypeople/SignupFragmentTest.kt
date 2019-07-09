package com.teambloodformypeople

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.teambloodformypeople.fragments.SecuritySignupFragment
import com.teambloodformypeople.fragments.SecuritySignupFragmentDirections
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class SignupFragmentTest {

    @Rule
    @JvmField
    val mactivity = ActivityTestRule<MainActivity>(MainActivity::class.java)



    @Test
    fun testNavigationToHomeFromSignupScreen(){
        val sign = SecuritySignupFragment()
        mactivity.activity.supportFragmentManager.beginTransaction().replace(R.id.my_nav_host_fragment,sign).commit()



//
//
//        val mockNavController = mock(NavController::class.java)
//        val scenario = launchFragmentInContainer<SecuritySignupFragment>(Bundle(),R.style.AppTheme)
//        scenario.onFragment {
//            Navigation.setViewNavController(it.view!!,mockNavController)
//        }
//        onView(withId(R.id.Signup_button)).perform(click())
////
////        verify(mockNavController).navigate(
////            SecuritySignupFragmentDirections
////        )
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