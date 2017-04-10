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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewRatingFeedbackTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void viewRatingFeedbackTest() {
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

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_ratings), withText("Ratings"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_view_my_ratings_seeker), withText("View My Ratings"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        withId(R.id.seekerRatingslist),
                        0),
                        isDisplayed()));
        linearLayout2.perform(click());

        pressBack();

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        withId(R.id.seekerRatingslist),
                        1),
                        isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.desc1), withText("Average rating:"),
                        childAtPosition(
                                allOf(withId(R.id.averageField),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Average rating:")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.desc2), withText("Attitude:"),
                        childAtPosition(
                                allOf(withId(R.id.rating1Field),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Attitude:")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.desc3), withText("Punctuality:"),
                        childAtPosition(
                                allOf(withId(R.id.rating2Field),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                2)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Punctuality:")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.desc4), withText("Cleanliness:"),
                        childAtPosition(
                                allOf(withId(R.id.rating3Field),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                3)),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Cleanliness:")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.desc5), withText("Comment:"),
                        childAtPosition(
                                allOf(withId(R.id.commentField),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                4)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Comment:")));

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
