package com.example.shopping

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailsActivityTest {

    @Test
    fun testProductDetailsActivityGeneral() {
        ActivityScenario.launch(ProductDetailsActivity::class.java)

        onView(withId(R.id.product_details)).check(matches(isDisplayed()))
        onView(withId(R.id.backButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackButton_navigatesToMainActivity() {
        Intents.init()

        ActivityScenario.launch(ProductDetailsActivity::class.java)

        val backButton = onView(withId(R.id.backButton))
        backButton.perform(click())

        intended(hasComponent(MainActivity::class.java.name))

        onView(withId(R.id.textWelcome)).check(matches(isDisplayed()))
        onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
    }

}