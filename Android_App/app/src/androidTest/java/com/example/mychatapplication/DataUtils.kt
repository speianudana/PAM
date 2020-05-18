package com.example.mychatapplication

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Matcher


class DataUtils {
    companion object {
        fun waitId(viewId: Int, millis: Long): ViewAction? {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return isRoot()
                }

                override fun getDescription(): String {
                    return "wait for a specific view with id <$viewId> during $millis millis."
                }

                override fun perform(uiController: UiController, view: View?) {
                    uiController.loopMainThreadUntilIdle()
                    val startTime = System.currentTimeMillis()
                    val endTime = startTime + millis
                    val viewMatcher: Matcher<View> = withId(viewId)
                    do {
                        for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                            if (viewMatcher.matches(child)) {
                                return
                            }
                        }
                        uiController.loopMainThreadForAtLeast(50)
                    } while (System.currentTimeMillis() < endTime)
                }
            }
        }
    }
}
