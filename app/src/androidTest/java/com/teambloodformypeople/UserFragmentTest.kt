package com.teambloodformypeople

//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class UserFragmentTest {

    @Test
    fun testNavigationToSignInScreen() {
        val mockNavController = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<UserFragment>()


        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, mockNavController)
        }

        onView(withId(R.id.login_button)).perform(click())
        verify(mockNavController).navigate(R.id.donor_home_des)




    }}