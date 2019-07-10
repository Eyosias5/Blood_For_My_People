package com.teambloodformypeople

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.teambloodformypeople.fragments.ProfileFragment
import com.teambloodformypeople.fragments.RecyclerViewDonationHistoryFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class DonationFragmentTest{
    @Test
    fun testRecylerViewofDonationHistory(){
        val mockNavController = mock(NavController::class.java)
     val scenario = launchFragmentInContainer<RecyclerViewDonationHistoryFragment>(Bundle(),R.style.AppTheme)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(),mockNavController)
        }
       onView(withId(R.id.recycler_view_history))
           .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

    }
}