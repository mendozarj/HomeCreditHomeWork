package com.example.homecredittest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.homecredittest.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class ApplicationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    private val latch: CountDownLatch = CountDownLatch(1)

    @Inject
    lateinit var testBinding: String

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun showHiltTestString() {
        assertThat(testBinding, containsString("Legend!"))
    }

    @Test
    fun showString() {
        assertEquals(testBinding, "I am Legend!")
    }

    @Module
    @InstallIn(ApplicationComponent::class)
    object TestBinding {
        @Singleton
        @Provides
        fun providesDefaultString(): String = "I am Legend!"
    }

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(ViewMatchers.withText("Weather Forecast")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        latch.await(1, TimeUnit.SECONDS)
        onView(withId(R.id.manila)).perform(click())

        onView(ViewMatchers.withText("Weather Details")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.fav)).perform(click())

        latch.await(1, TimeUnit.SECONDS)
        onView(withId(R.id.refresh)).perform(swipeDown())

        latch.await(1, TimeUnit.SECONDS)
        onView(withId(R.id.backButton)).perform(click())

        onView(withId(R.id.refresh)).perform(swipeDown())
        latch.await(2, TimeUnit.SECONDS)
    }
}