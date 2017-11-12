package com.grow.cmputf17team4.grow;

import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.grow.cmputf17team4.grow.Views.ActivityMain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class US010101Test extends ActivityInstrumentationTestCase2 {
    Activity activity;

    private String name;
    private String reason;
    private int requestCode;
    private static final String PACKAGE_NAME = "com.grow.cmputf17team4.grow.Views";

    public US010101Test(Class activityClass, Activity activity) {
        super(ActivityMain.class);
    }

    @Rule
    public IntentsTestRule<ActivityMain> mIntentsRule =
            new IntentsTestRule<>(ActivityMain.class);

    @Before
    public void initialize(){
        name = new String("Name");
        reason = new String("reason");
        requestCode = 1;
    }

    @Test
    public void verifyMessageSentToMessageActivity(){

        onView(withId(R.id.add_habit)).perform(click());

        intended(allOf(
                hasComponent(hasShortClassName(".ActivityModiyHabit")),
                toPackage(PACKAGE_NAME),
                hasExtra("1", requestCode)));
    }



}

