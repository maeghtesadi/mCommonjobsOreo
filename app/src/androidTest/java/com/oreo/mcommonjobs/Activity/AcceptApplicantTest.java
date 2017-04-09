package com.oreo.mcommonjobs.Activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.oreo.mcommonjobs.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AcceptApplicantTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void acceptApplicantTest() {
        ViewInteraction rc = onView(
                allOf(withText("Sign in"),
                        withParent(withId(R.id.sign_in_button)),
                        isDisplayed()));
        rc.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_view_applicants), withText("View Applicants"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.applicantslist),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.acceptApplicant), withText("Accept Applicant"), isDisplayed()));
        appCompatButton2.perform(click());

        pressBack();

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
