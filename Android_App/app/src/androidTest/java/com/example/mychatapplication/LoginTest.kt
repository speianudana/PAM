package com.example.mychatapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.mychatapplication.DataUtils.Companion.waitId
import com.example.mychatapplication.screens.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @JvmField
    @Rule
    var rule = ActivityTestRule(LoginActivity::class.java, true, true);

    @Test
    fun loginScreen_shouldSuccessfulLogin() {
        onView(ViewMatchers.withId(R.id.username))
            .perform(ViewActions.typeText("admin"))
        onView(ViewMatchers.withId(R.id.password))
            .perform(ViewActions.typeText("admin"))
        onView(ViewMatchers.withId(R.id.login_button_id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())
        //wait 3 seconds for home navigation bar
        onView(isRoot()).perform(waitId(R.id.bottom_navigation, 3000));
        onView(ViewMatchers.withId(R.id.bottom_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
