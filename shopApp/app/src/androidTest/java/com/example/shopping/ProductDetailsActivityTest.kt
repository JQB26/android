package com.example.shopping

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailsActivityTest {

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun testBackButton_navigatesToMainActivity() {
        ActivityScenario.launch(ProductDetailsActivity::class.java)

        val backButton = onView(withId(R.id.backButton))

        backButton.perform(click())

        intended(hasComponent(MainActivity::class.java.name))
    }

}