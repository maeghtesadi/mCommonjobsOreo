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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ApplicationFormTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void applicationFormTest() {
        ViewInteraction rc = onView(
                allOf(withText("Sign in"),
                        withParent(withId(R.id.sign_in_button)),
                        isDisplayed()));
        rc.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.viewprofiles),
                        4),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        withId(R.id.viewprofiles),
                        4),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_view_jobs_for_your_profile), withText("ViewJobs profile jobs"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.joblist),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnApply), withText("Apply"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.apply),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.yearsOption1),
                        childAtPosition(
                                allOf(withId(R.id.yearsEXP),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));

        ViewInteraction radioButton2 = onView(
                allOf(withId(R.id.yearsOption2),
                        childAtPosition(
                                allOf(withId(R.id.yearsEXP),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        radioButton2.check(matches(isDisplayed()));

        ViewInteraction radioButton3 = onView(
                allOf(withId(R.id.yearsOption3),
                        childAtPosition(
                                allOf(withId(R.id.yearsEXP),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                2),
                        isDisplayed()));
        radioButton3.check(matches(isDisplayed()));

        ViewInteraction radioButton4 = onView(
                allOf(withId(R.id.avalibityOption1),
                        childAtPosition(
                                allOf(withId(R.id.availability),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                3)),
                                0),
                        isDisplayed()));
        radioButton4.check(matches(isDisplayed()));

        ViewInteraction radioButton5 = onView(
                allOf(withId(R.id.avalibityOption2),
                        childAtPosition(
                                allOf(withId(R.id.availability),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                3)),
                                1),
                        isDisplayed()));
        radioButton5.check(matches(isDisplayed()));

        ViewInteraction radioButton6 = onView(
                allOf(withId(R.id.avalibityOption3),
                        childAtPosition(
                                allOf(withId(R.id.availability),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                3)),
                                2),
                        isDisplayed()));
        radioButton6.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Enter expected wage"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        textView.check(matches(withText("Enter expected wage")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.apply),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

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
