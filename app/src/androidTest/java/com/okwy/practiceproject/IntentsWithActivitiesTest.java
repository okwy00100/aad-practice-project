package com.okwy.practiceproject;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.okwy.practiceproject.IntentsWithActivities.IntentActivity;
import com.okwy.practiceproject.Main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IntentsWithActivitiesTest {

    @Rule
    public ActivityScenarioRule<IntentActivity> activityRule = new ActivityScenarioRule<IntentActivity>(IntentActivity.class);

    @Test
    public void activityLaunch() {
        onView(withId(R.id.send_button)).perform(click());
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
        onView(withId(R.id.replyButton)).perform(click());
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
    }
}
