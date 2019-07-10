package com.teambloodformypeople

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.teambloodformypeople.fragments.RecyclerViewUserListFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {
    @Test
    fun testRecyclerViewofUserList(){
        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<RecyclerViewUserListFragment>(Bundle(),R.style.AppTheme)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(),mockNavController)
        }
        onView(withId(R.id.recycler_view_user_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        val itemElementText = "eyosiasamson5@gmail.com"
        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }
}