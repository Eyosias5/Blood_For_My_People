package com.teambloodformypeople

//import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.rule.ActivityTestRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.teambloodformypeople.fragments.ProfileFragment
import com.teambloodformypeople.fragments.RecyclerViewRecepientListFragment
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class BottomNavigationTest{

    @Rule
    @JvmField
    val mactivity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun CheckHomeScreen(){

        val recepientListFragment = RecyclerViewRecepientListFragment()
        mactivity.activity.supportFragmentManager.beginTransaction().replace(R.id.layout2,recepientListFragment).commit()
        onView(withId(R.id.bottom_nav_view)).perform(click())



//        val mockNavController = mock(NavController::class.java)
//        val scenario = launchFragmentInContainer {
//            ProfileFragment().also { fragment->
//
//                fragment.viewLifecycleOwnerLiveData.observeForever{viewLifecycleOwner ->
//                if (viewLifecycleOwner != null) {
//                    // The fragment’s view has just been created
//                    Navigation.setViewNavController(fragment.requireView(), mockNavController)
//                }}
//            }
//        }
//        onView(withId(R.id.signOutButton)).perform(click())
//        verify(mockNavController).navigate(R.id.signOutAction)

    }
}