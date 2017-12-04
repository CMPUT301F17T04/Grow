package com.grow.cmputf17team4.grow.Models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


/**
 * Created by chris on 2017-12-04.
 */
public class HabitListUnitTest{
    private HabitList habitList;
    private final String id = Constant.TEST_USER_NAME;
    @Before
    public void setUp(){
        DataManager.prepareForTesting();
    }
    @Test
    public void testAddHabit(){
        habitList = DataManager.getInstance().getHabitList();
        int size = habitList.size();
        HabitType habitType = new HabitType(id);
        DataManager.getInstance().getHabitList().add(habitType);
        habitList.add(habitType);
        assertEquals(habitList.size(),size+1);
    }
    @Test
    public void testRemoveEvent(){
        habitList = DataManager.getInstance().getHabitList();
        int size = habitList.size();
        HabitType habitType = new HabitType(id);
        habitList.add(habitType);
        assertEquals(habitList.size(),size+1);
        habitList.remove(habitType.getUid());
        assertEquals(habitList.size(),size);
    }
}