package com.okwy.practiceproject;

import android.service.autofill.Validator;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.okwy.practiceproject.Main.MainActivity;
import com.okwy.practiceproject.Toast.ToastActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class ToastSelectionTest {

    private View inDecorView;

    @Rule
    public ActivityScenarioRule<ToastActivity> activityRule = new ActivityScenarioRule<>(ToastActivity.class);


    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<ToastActivity>() {
            @Override
            public void perform(ToastActivity activity) {
                inDecorView = activity.getWindow().getDecorView();
            }

        });
    }

    @Test
    public void clickOnToastProvidesDefaultToastMessage(){

        onView(withId(R.id.buttonToast))
                .perform(click());

        onView(withText("Testing Toast"))
               // .inRoot(withDecorView(is(not(inDecorView)))) //TODO : Needs to be fixed. If anyone can help please...
                .check(matches(isDisplayed()));

    }

}
