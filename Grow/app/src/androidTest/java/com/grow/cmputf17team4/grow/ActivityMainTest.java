package com.grow.cmputf17team4.grow;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.grow.cmputf17team4.grow.Views.ActivityMain;
import com.grow.cmputf17team4.grow.Views.ActivityModifyHabit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

/**
 * Created by chris on 2017-11-11.
 */
public class ActivityMainTest {
    @Rule
    public ActivityTestRule<ActivityMain> activityMainTestRule = new ActivityTestRule<ActivityMain>(ActivityMain.class);
    private ActivityMain activityM = null;

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().addMonitor(ActivityModifyHabit.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {
        activityM = activityMainTestRule.getActivity();

    }

    @Test
    public void testTheAddButton(){
        assertNotNull(activityM.findViewById(R.id.add_habit));
        Espresso.onView(ViewMatchers.withId(R.id.add_habit)).perform(ViewActions.click());

        Activity addHabit = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(addHabit);
        addHabit.finish();

    }

    @After
    public void tearDown() throws Exception {
        activityM = null;
    }

}