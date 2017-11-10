package com.grow.cmputf17team4.grow;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.grow.cmputf17team4.grow.Views.ActivityMain;
import com.grow.cmputf17team4.grow.Views.ActivityModifyHabit;

/**
 * Created by chris on 2017-11-09.
 */

public class US010101Test extends ActivityInstrumentationTestCase2 {
    Activity activity;

    public US010101Test(Class activityClass, Activity activity) {
        super(ActivityMain.class);
    }

    public void testAddButton(){
        activity = getActivity();


        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(
                ActivityModifyHabit.class.getName(),null,false);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button =  (Button) activity.findViewById(R.id.add_habit);
                button.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        activity = am.waitForActivityWithTimeout(1000);
        getInstrumentation().removeMonitor(am);
        assertEquals(, );

    }
}
