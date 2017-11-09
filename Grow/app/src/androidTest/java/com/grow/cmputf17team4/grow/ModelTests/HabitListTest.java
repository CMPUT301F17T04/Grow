package com.grow.cmputf17team4.grow.ModelTests;

import android.test.ActivityInstrumentationTestCase2;

import com.grow.cmputf17team4.grow.Models.HabitList;
import com.grow.cmputf17team4.grow.Models.HabitType;

/**
 * Created by Infrared on 2017-11-08.
 */

public class HabitListTest extends ActivityInstrumentationTestCase2 {
    public HabitListTest(){
        super(HabitList.class);
    }
    public void testAddHabitType(){
        HabitType habitType= new HabitType();
        assertEquals("HabitType.uuid Error", habitType.getUid());

    }

}
