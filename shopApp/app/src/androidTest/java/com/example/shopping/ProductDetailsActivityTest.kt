package com.example.shopping

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductDetailsActivityTest {

    @Before
    @Throws(Exception::class)
    fun setUp() {
        Intents.init()
    }

    @Test
    fun testProductDetailsActivityGeneral() {
        ActivityScenario.launch(ProductDetailsActivity::class.java)

        onView(withId(R.id.product_details)).check(matches(isDisplayed()))
        onView(withId(R.id.backButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackButton_navigatesToMainActivity() {
        ActivityScenario.launch(ProductDetailsActivity::class.java)

        val backButton = onView(withId(R.id.backButton))
        backButton.perform(click())

        intended(hasComponent(MainActivity::class.java.name))

        onView(withId(R.id.textWelcome)).check(matches(isDisplayed()))
        onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackButton_navigatesToMainActivity_ProductFragment() {
        ActivityScenario.launch<ProductDetailsActivity>(
            ProductDetailsActivity.newIntent(ApplicationProvider.getApplicationContext(), "ProductFragment")
        ).use {
            val backButton = onView(withId(R.id.backButton))
            backButton.perform(click())

            intended(hasComponent(MainActivity::class.java.name))

            onView(withId(R.id.product_list)).check(matches(isDisplayed()))
            onView(withId(R.id.home)).check(matches(isDisplayed()))
            onView(withId(R.id.category)).check(matches(isDisplayed()))
            onView(withId(R.id.cart)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testBackButton_navigatesToMainActivity_CategoryFragment() {
        ActivityScenario.launch<ProductDetailsActivity>(
            ProductDetailsActivity.newIntent(ApplicationProvider.getApplicationContext(), "CategoryFragment")
        ).use {
            val backButton = onView(withId(R.id.backButton))
            backButton.perform(click())

            intended(hasComponent(MainActivity::class.java.name))

            onView(withId(R.id.category_list)).check(matches(isDisplayed()))
            onView(withId(R.id.home)).check(matches(isDisplayed()))
            onView(withId(R.id.category)).check(matches(isDisplayed()))
            onView(withId(R.id.cart)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testBackButton_navigatesToMainActivity_CartFragment() {
        ActivityScenario.launch<ProductDetailsActivity>(
            ProductDetailsActivity.newIntent(ApplicationProvider.getApplicationContext(), "CartFragment")
        ).use {
            val backButton = onView(withId(R.id.backButton))
            backButton.perform(click())

            intended(hasComponent(MainActivity::class.java.name))

            onView(withId(R.id.cart_list)).check(matches(isDisplayed()))
            onView(withId(R.id.payButton)).check(matches(isDisplayed()))
            onView(withId(R.id.home)).check(matches(isDisplayed()))
            onView(withId(R.id.category)).check(matches(isDisplayed()))
            onView(withId(R.id.cart)).check(matches(isDisplayed()))
        }
    }

    @After
    @Throws(java.lang.Exception::class)
    fun tearDown() {
        Intents.release()
    }

}