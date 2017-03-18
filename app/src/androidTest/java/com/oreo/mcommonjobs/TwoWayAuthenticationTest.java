package com.oreo.mcommonjobs;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Activtity.TwilioAuthenticationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Sam on 2017-03-17.
 */


@RunWith(AndroidJUnit4.class)

public class TwoWayAuthenticationTest {

    @Rule
    public ActivityTestRule<TwilioAuthenticationActivity> mTwilioAuth = new ActivityTestRule(TwilioAuthenticationActivity.class);

    @Test
    public void TestPhoneNumberInput(){
        onView(withId(R.id.editText_phoneNumber)).perform(typeText("+12345678901")).check(matches(isDisplayed()));
    }

    @Test
    public void TestConfirmationNumberInput(){
        onView(withId(R.id.editText_confirmation_number)).perform(typeText("123456")).check(matches(isDisplayed()));
    }

}
