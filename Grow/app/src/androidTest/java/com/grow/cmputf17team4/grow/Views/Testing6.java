package com.grow.cmputf17team4.grow.Views;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.grow.cmputf17team4.grow.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Testing6 {

    @Rule
    public ActivityTestRule<ActivityMain> mActivityTestRule = new ActivityTestRule<>(ActivityMain.class);

    @Test
    public void testing6() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_event),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation_main),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.viewpager_main),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_community),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation_main),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.viewpager_main),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_habit),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation_main),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.viewpager_main),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        viewPager3.perform(swipeRight());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.icon),
                        childAtPosition(
                                allOf(withId(R.id.navigation_habit),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

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
