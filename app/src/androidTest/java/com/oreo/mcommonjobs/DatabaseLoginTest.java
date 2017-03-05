package com.oreo.mcommonjobs;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.oreo.mcommonjobs.Activtity.CreateJobPostAcitvity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jason on 2017-02-26.
 */




@RunWith(AndroidJUnit4.class)
public class DatabaseLoginTest {

    @Rule
    public ActivityTestRule<CreateJobPostAcitvity> mActivityRule = new ActivityTestRule(CreateJobPostAcitvity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withId(R.id.editText)).perform(typeText("hello")).check(matches(isDisplayed()));


    }
}





