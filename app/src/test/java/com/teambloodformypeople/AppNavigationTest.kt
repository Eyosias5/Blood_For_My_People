//package com.teambloodformypeople
//
//
//import androidx.test.core.app.ActivityScenario
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.junit.MockitoJUnitRunner
//import java.util.regex.Pattern.matches
//
//@RunWith(MockitoJUnitRunner::class)
//class  AppNavigationTest{
//
//    @Test
//    fun bottomNavigationFromHomeToHistory(){
//        val activityScenario =  ActivityScenario.launch(MainActivity::class.java)
//        onView(withId(R.id.bottom_nav_view))
//            .perform(navigateTo(R.id.history_des))
//
//        onView(withId(R.id.bottom_nav_view))
//            .perform(navigateTo(R.id.DonationHistoryFragment))
//
//        onView(withId(R.id.history_des)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun bottomNavigationFromHomeToReports(){
//        val activityScenario =  ActivityScenario.launch(MainActivity::class.java)
//        onView(withId(R.id.bottom_nav_view))
//            .perform(navigateTo(R.id.report_des))
//
//        onView(withId(R.id.bottom_nav_view))
//            .perform(navigateTo(R.id.ReportFragment))
//
//        onView(withId(R.id.repot_des)).check(matches(isDisplayed()))
//
//    }
//
//    @Test
//    fun bottomNavigationFromHomeToProfile(){
//
//        val activityScenario =  ActivityScenario.launch(MainActivity::class.java)
//        onView(withId(R.id.bottom_nav_view))
//            .perform(navigateTo(R.id.profile_des))
//
//        onView(withId(R.id.bottom_nav_view))
//            .perform(navigateTo(R.id.UserFragment))
//
//        onView(withId(R.id.repot_des)).check(matches(isDisplayed()))
//    }
//
//
//}