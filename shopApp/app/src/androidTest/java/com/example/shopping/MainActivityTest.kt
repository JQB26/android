package com.example.shopping

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shopping.fragments.CartFragment
import com.example.shopping.fragments.CategoryFragment
import com.example.shopping.fragments.ProductFragment
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testMyActivityGeneral() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "ProductFragment")
        ).use {
            onView(withId(R.id.textWelcome)).check(matches(isDisplayed()))
            onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))
            onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testDestinationOnIntent_ProductFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "ProductFragment")
        ).use { scenario ->
            scenario.onActivity { activity ->
                val frameLayout = activity.supportFragmentManager.findFragmentById(R.id.frame_layout)
                assertEquals(frameLayout?.javaClass, ProductFragment::class.java)
            }
        }
    }

    @Test
    fun testDestinationOnIntent_CategoryFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "CategoryFragment")
        ).use { scenario ->
            scenario.onActivity { activity ->
                val frameLayout = activity.supportFragmentManager.findFragmentById(R.id.frame_layout)
                assertEquals(frameLayout?.javaClass, CategoryFragment::class.java)
            }
        }
    }

    @Test
    fun testDestinationOnIntent_CartFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "CartFragment")
        ).use { scenario ->
            scenario.onActivity { activity ->
                val frameLayout = activity.supportFragmentManager.findFragmentById(R.id.frame_layout)
                assertEquals(frameLayout?.javaClass, CartFragment::class.java)
            }
        }
    }

    @Test
    fun testBottomNavigation_ProductFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "ProductFragment")
        ).use {
            onView(withId(R.id.list)).check(matches(isDisplayed()))
            onView(withId(R.id.home)).check(matches(isDisplayed()))
            onView(withId(R.id.category)).check(matches(isDisplayed()))
            onView(withId(R.id.cart)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testBottomNavigation_CategoryFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "CategoryFragment")
        ).use {
            onView(withId(R.id.list)).check(matches(isDisplayed()))
            onView(withId(R.id.home)).check(matches(isDisplayed()))
            onView(withId(R.id.category)).check(matches(isDisplayed()))
            onView(withId(R.id.cart)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testBottomNavigation_CartFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "CartFragment")
        ).use {
            onView(withId(R.id.list)).check(matches(isDisplayed()))
            onView(withId(R.id.payButton)).check(matches(isDisplayed()))
            onView(withId(R.id.home)).check(matches(isDisplayed()))
            onView(withId(R.id.category)).check(matches(isDisplayed()))
            onView(withId(R.id.cart)).check(matches(isDisplayed()))
        }
    }

    // TODO: mock backend for the data fetching
    @Test
    fun testProductFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "ProductFragment")
        ).use {
            onView(withText("Brass birmingham")).check(matches(isDisplayed()))
            onView(withText("chess")).check(matches(isDisplayed()))
            onView(withText("Civilization 6")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testCategoryFragment() {
        ActivityScenario.launch<MainActivity>(
            MainActivity.newIntent(getApplicationContext(), "CategoryFragment")
        ).use {
            onView(withText("Boardgames")).check(matches(isDisplayed()))
            onView(withText("Video Games")).check(matches(isDisplayed()))
        }
    }

}

