package com.teambloodformypeople

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.teambloodformypeople.fragments.RecyclerViewDonationHistoryFragment
import com.teambloodformypeople.fragments.RecyclerViewRecepientListFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class ReceptionFragmentTest {
    @Test
    fun testRecyclerViewReception(){
        val mockNavController = Mockito.mock(NavController::class.java)
        val scenario = launchFragmentInContainer<RecyclerViewRecepientListFragment>(Bundle(),R.style.AppTheme)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(),mockNavController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_recepient_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

    }


    }

