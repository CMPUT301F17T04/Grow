package com.grow.cmputf17team4.grow;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.widget.TextView;

import com.grow.cmputf17team4.grow.Controllers.DataManager;
import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.HabitType;
import com.grow.cmputf17team4.grow.Views.ActivityMain;
import com.grow.cmputf17team4.grow.Views.ActivityModifyEvent;
import com.grow.cmputf17team4.grow.Views.ActivityModifyHabit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertNotNull;

/**
 * Created by chris on 2017-11-11.
 */
public class CompleteTest {
    @Rule
    public ActivityTestRule<ActivityMain> activityMainTestRule = new ActivityTestRule<ActivityMain>(ActivityMain.class);
    private ActivityMain activityM = null;

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().addMonitor(ActivityModifyHabit.class.getName(),null,false);




    @Before
    public void setUp() throws Exception {
        DataManager.getInstance().getHabitList().clear();

        activityM = activityMainTestRule.getActivity();
    }

    @Test
    public void testTheAddButton(){

        assertNotNull(activityM.findViewById(R.id.toolbar_btn_add_habit));
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_btn_add_habit)).perform(ViewActions.click());

        Activity addbutton = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(addbutton);

        ViewInteraction habitEditName = Espresso.onView(ViewMatchers.withId(R.id.modify_habit_edit_name)).perform(ViewActions.typeText("basketball"));
        assertNotNull(habitEditName);

        ViewInteraction habitEditReason = Espresso.onView(ViewMatchers.withId(R.id.modify_habit_edit_reason)).perform(ViewActions.typeText("Love it"));
        assertNotNull(habitEditReason);


        Espresso.onView(ViewMatchers.withId(R.id.modify_habit_edit_reason)).perform(ViewActions.closeSoftKeyboard());

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        //System.out.println(day);
        switch (day) {
            case Calendar.SUNDAY:
                ViewInteraction dayOfWeek0 = Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_0)).perform(ViewActions.click());
                assertNotNull(dayOfWeek0);
                break;

            case Calendar.MONDAY:
                // Current day is Monday
                ViewInteraction dayOfWeek1 =Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_1)).perform(ViewActions.click());
                assertNotNull(dayOfWeek1);
                break;


            case Calendar.TUESDAY:
                // Tuesday
                ViewInteraction dayOfWeek2 =Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_2)).perform(ViewActions.click());
                assertNotNull(dayOfWeek2);
                break;

            case Calendar.WEDNESDAY:
                ViewInteraction dayOfWeek3 =Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_3)).perform(ViewActions.click());
                assertNotNull(dayOfWeek3);
                break;

            case Calendar.THURSDAY:
                ViewInteraction dayOfWeek4 =Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_4)).perform(ViewActions.click());
                assertNotNull(dayOfWeek4);
                break;

            case Calendar.FRIDAY:
                ViewInteraction dayOfWeek5 =Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_5)).perform(ViewActions.click());
                assertNotNull(dayOfWeek5);
                break;

            case Calendar.SATURDAY:
                ViewInteraction dayOfWeek6 =Espresso.onView(ViewMatchers.withId(R.id.modify_habit_checkbox_6)).perform(ViewActions.click());
                assertNotNull(dayOfWeek6);
                break;

        }

        ViewInteraction habitBtnConfirm = Espresso.onView(ViewMatchers.withId(R.id.modify_habti_btn_confirm)).perform(ViewActions.click());
        //assertion
        assertNotNull(habitBtnConfirm);


        onData(anything()).inAdapterView(allOf(withId(R.id.card_list_view),isDisplayed()))
        .atPosition(0).onChildView (withId(R.id.list_item_title)).check(matches(withText("basketball")));



        ViewInteraction habitListItemBtnComplete = onData(anything()).inAdapterView(allOf(withId(R.id.card_list_view),isDisplayed()))
                .atPosition(0).onChildView (withId(R.id.list_item_btn_complete)).perform(click());
        assertNotNull(habitListItemBtnComplete);

        ViewInteraction completeEventEditComment = Espresso.onView(ViewMatchers.withId(R.id.modify_event_edit_comment)).perform(ViewActions.typeText("tired"));
        assertNotNull(completeEventEditComment);

        Espresso.onView(ViewMatchers.withId(R.id.modify_event_edit_comment)).perform(ViewActions.closeSoftKeyboard());




        ViewInteraction confirmBtn = Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click());
        assertNotNull(confirmBtn);



        ViewInteraction editExistHabit = onData(anything()).inAdapterView(allOf(withId(R.id.card_list_view), isDisplayed()))
                .atPosition(0).perform(click());
        assertNotNull(editExistHabit);

        ViewInteraction habitChangeName = Espresso.onView(ViewMatchers.withId(R.id.modify_habit_edit_name)).perform(ViewActions.replaceText("zhai"));
        assertNotNull(habitChangeName);

        ViewInteraction habitBtnConfirm1 = Espresso.onView(ViewMatchers.withId(R.id.modify_habti_btn_confirm)).perform(ViewActions.click());
        //assertion
        assertNotNull(habitBtnConfirm1);

        onData(anything()).inAdapterView(allOf(withId(R.id.card_list_view),isDisplayed()))
                .atPosition(0).onChildView (withId(R.id.list_item_title)).check(matches(withText("zhai")));

        ViewInteraction editExistHabit1 = onData(anything()).inAdapterView(allOf(withId(R.id.card_list_view), isDisplayed()))
                .atPosition(0).perform(click());
        assertNotNull(editExistHabit1);

        ViewInteraction habitChangeReason = Espresso.onView(ViewMatchers.withId(R.id.modify_habit_edit_reason)).perform(ViewActions.replaceText("kill him"));
        assertNotNull(habitChangeReason);

        ViewInteraction habitBtnConfirm2 = Espresso.onView(ViewMatchers.withId(R.id.modify_habti_btn_confirm)).perform(ViewActions.click());
        //assertion
        assertNotNull(habitBtnConfirm2);

        ViewInteraction editExistHabit2 = onData(anything()).inAdapterView(allOf(withId(R.id.card_list_view), isDisplayed()))
                .atPosition(0).perform(click());
        assertNotNull(editExistHabit2);

        onView(withId(R.id.modify_habit_edit_name)).check(matches(withText("zhai")));
        onView(withId(R.id.modify_habit_edit_reason)).check(matches(withText("kill him")));

        ViewInteraction deleteButton = Espresso.onView(ViewMatchers.withId(R.id.modify_habit_btn_delete)).perform(ViewActions.click());
        assertNotNull(deleteButton);

        addbutton.finish();


    }


    @After
    public void tearDown() throws Exception {
        activityM = null;
        DataManager.getInstance().getHabitList().clear();
    }


}