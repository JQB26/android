package com.example.shopping

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PaymentActivityTest {

    @Test
    fun testPaymentActivityGeneral() {
        ActivityScenario.launch(PaymentActivity::class.java)

        checkAllTheFields()
    }

    @Test
    fun testAcceptPaymentButton_missingInputs() {
        Intents.init()

        ActivityScenario.launch(PaymentActivity::class.java)

        val acceptPaymentButton = onView(withId(R.id.acceptPayment))
        acceptPaymentButton.perform(ViewActions.click())

        Intents.intended(IntentMatchers.hasComponent(PaymentActivity::class.java.name))

        checkAllTheFields()
    }

    private fun checkAllTheFields() {
        onView(withId(R.id.textInputSurname)).check(matches(isDisplayed()))
        onView(withId(R.id.textInputSurname))
            .check(matches(hasDescendant(withHint(R.string.surname))))

        onView(withId(R.id.textInputName)).check(matches(isDisplayed()))
        onView(withId(R.id.textInputName))
            .check(matches(hasDescendant(withHint(R.string.name))))

        onView(withId(R.id.textInputCardNumber)).check(matches(isDisplayed()))
        onView(withId(R.id.textInputCardNumber))
            .check(matches(hasDescendant(withHint(R.string.card_number))))

        onView(withId(R.id.textInputCVV)).check(matches(isDisplayed()))
        onView(withId(R.id.textInputCVV))
            .check(matches(hasDescendant(withHint(R.string.cvv))))

        onView(withId(R.id.totalPrice)).check(matches(isDisplayed()))

        onView(withId(R.id.acceptPayment)).check(matches(isDisplayed()))

        onView(withId(R.id.editTextExpiryDate)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextExpiryDate)).check(matches(withHint(R.string.card_expiry_date)))

        onView(withId(R.id.total_price_text)).check(matches(isDisplayed()))
        onView(withId(R.id.total_price_text)).check(matches(withText(R.string.total_price_is)))
    }
}
