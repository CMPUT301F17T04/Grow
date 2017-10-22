package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by qin7 on 2017/10/22.
 */

public class HabitTypeTest extends ActivityInstrumentationTestCase2 {
    public HabitTypeTest() {
        super(com.grow.cmputf17team4.grow.HabitType.class);
    }
    public void testGetName() {
        HabitType atype = new HabitType("haha");
        assertEquals("HabitType.GetHabitTypeName Error", atype.getName(), "haha");
    }

    public void testSetName() {
        HabitType atype = new HabitType("haha");
        atype.setName("Dumb");
        assertEquals("HabitType.SetName Error", atype.getName(), "Dumb");
    }

    public void testGetId(){
        HabitType atype = new HabitType("haha");
        assertEquals("HabitType.GetId Error", atype.getId(), 1);
    }

    public void testSetId(){
        HabitType atype = new HabitType("haha");
        atype.setId(23333);
        assertEquals("HabitType.SetId Error", atype.getId(), 23333);
    }

    public void testGetReason(){
        HabitType atype = new HabitType("haha");
        assertEquals("HabitType.GetReason Error", atype.getReason(), "");
    }

    public void testSetReason(){
        HabitType atype  = new HabitType("haha");
        atype.setReason("10");
        assertEquals("HabitType.SetReason Error", atype.getReason(), "10");
    }
    public void testGetNumCompleted(){
        HabitType atype  = new HabitType("haha");
        assertEquals("HabitType atype .GetNumCompleted Error", atype.getNumCompleted(), 0);
    }

    public void testSetNumCompleted(){
        HabitType atype = new HabitType("haha");
        atype.setNumCompleted(10);
        assertEquals("HabitType.SetNumCompleted Error", atype.getNumCompleted(), 10);
    }

    public void testGetNumMissed(){
        HabitType atype = new HabitType("haha");
        assertEquals("HabitType.GetNumMissed Error", atype.getNumMissed(), 0);
    }

    public void testSetNumMissed(){
        HabitType atype = new HabitType("haha");
        atype.setNumMissed(10);
        assertEquals("HabitType.SetNumMissed Error", atype.getNumMissed(), 10);
    }
    public void testGetUserId(){
        HabitType atype = new HabitType("haha");
        assertEquals("HabitType.GetUserId Error", atype.getUserId(), 1);
    }

    public void testSetUserId(){
        HabitType atype = new HabitType("haha");
        atype.setUserId(23333);
        assertEquals("HabitType.SetUserId Error", atype.getUserId(), 23333);
    }
    public void testGetRoutine(){
        HabitType atype = new HabitType("haha");
        boolean routine[] = {true};
        assertTrue("HabitType.GetRoutine Error", Arrays.equals(routine,atype.getRoutine()));
    }

    public void testSetRoutine(){
        HabitType atype = new HabitType("haha");
        boolean barray[] = {true,false};
        atype.setRoutine(barray);
        assertEquals("HabitType.SetRoutine Error", atype.getRoutine(), barray);
    }

}
