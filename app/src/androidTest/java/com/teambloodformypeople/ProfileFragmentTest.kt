package com.teambloodformypeople

//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.teambloodformypeople.fragments.ProfileFragment
import com.teambloodformypeople.fragments.ProfileFragmentDirections
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    @Test
    fun testNavigationToSignInScreenFromProfile(){

        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer {
            ProfileFragment().also { fragment->

                fragment.viewLifecycleOwnerLiveData.observeForever{viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }}
            }
        }
        onView(withId(R.id.signOutButton)).perform(click())
        verify(mockNavController).navigate(R.id.signOutAction)

    }


}