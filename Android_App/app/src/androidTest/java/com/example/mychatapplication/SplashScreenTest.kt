package com.example.mychatapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.mychatapplication.screens.login.LoginActivity
import com.example.mychatapplication.screens.splashScreen.SplashScreenActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @JvmField
    @Rule
    var rule = ActivityTestRule(SplashScreenActivity::class.java)

    @Test
    fun openSplashScreen_shouldShowIconLogo() {
        onView(ViewMatchers.withId(R.id.iv_logo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
