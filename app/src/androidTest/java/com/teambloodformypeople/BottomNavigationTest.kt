package com.teambloodformypeople

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomNavigationTest{

    @Test
    fun CheckHomeScreen(){

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.bottom_nav_view)).perform(navigateTo(R.id.home_des))
    }
}