package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by zhai on 10/19/17.
 */

public class UserTest extends ActivityInstrumentationTestCase2{

    public UserTest {
        super(com.grow.cmputf17team4.grow.UserActivity.class);
    }

    public void testAddHabit(){
        HabitList habits = new HabitList();
        Habit habit = new NewHabit("adding habit");
        habits.add(habit);
        assertTrue(habits.hasHabit(habit));
    }

    public void testDeleteHabit(){
        HabitList habits = new HabitList();
        Habit habit = new NewHabit("deleting habit");
        habits.add(habit);
        habits.delete(habit);
        assertFalse(habits.hasHabit(habit));
    }

    public void testGetHabit(){
        HabitList habits = new HabitList(); //
        Habit habit = new NewHabit("getting habit");
        habits.add(habit);
        Habit returnedHabit = habits.getHabit(0);
        assertEquals(returnedHabit.getName(), habit.getHabit());
    }

    public void testHasHabit(){
        HabitList list = new HabitList();
        Habit habit = new NewHabit("has habit");
        list.add(habit);
        assertTrue(list.hasHabit(habit));
    }
}
}
