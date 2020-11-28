package com.okwy.practiceproject;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.okwy.practiceproject.Main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainMenuSelectionTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void isRecyclerVisible(){
        onView(withId(R.id.mainRecycler)).check(matches(isDisplayed()));
    }

    @Test
    public void scrollDownCheckIfExist(){
        onView(withId(R.id.mainRecycler))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("App Settings and Preferences"))));
    }

    @Test
    public void testIfOnClickOnRecyclerWorks(){

    }

}
