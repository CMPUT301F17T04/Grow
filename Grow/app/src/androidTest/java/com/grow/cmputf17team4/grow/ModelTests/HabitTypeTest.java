package com.grow.cmputf17team4.grow.ModelTests;

import android.test.ActivityInstrumentationTestCase2;

import com.grow.cmputf17team4.grow.Models.HabitType;

import java.util.Arrays;

import static java.lang.Boolean.TRUE;

/**
 * Created by qin7 on 2017/10/22.
 */

public class HabitTypeTest extends ActivityInstrumentationTestCase2 {
    public HabitTypeTest() {
        super(HabitType.class);
    }

    public void testGetRepeat(){
        HabitType atype =new HabitType("HAHAH");
        assertEquals("HabitType.GetReapeat Error", atype.getRepeat(2));
    }
    public void testSetRepeat(){
        HabitType atype =new HabitType("haha");
        atype.setRepeat(2,TRUE);
        assertEquals("HabitType.SetRepeat Error", atype.getRepeat(2));
    }
    public void testGetUid(){
        HabitType atype =new HabitType("haha");
        assertEquals("HabitType.GetUid Error", atype.getUid(),1);
    }
    public void testGetStartDate(){
        HabitType atype= new HabitType("haha");
        assertEquals("HabitTyoe.GetStartDate Error",atype.getStartDate(),2);
    }
    public void testGetNextEventDay(){
        HabitType atype= new HabitType("haha");
        assertEquals("HabitType.GetNextDay Error",atype.getNextEventDay(),2);
    }
    public void testSetStartDate(){
        HabitType atype= new HabitType("haha");
        assertEquals("HabitType.SetStartDate Error", atype.getStartDate(), 2);
    }
    public void testHasEventToday(){
        HabitType atype = new HabitType("haha");
        boolean barray[] = {true,false};
        atype.hasEventToday();
        assertEquals("HabitType.SetRoutine Error", atype.hasEventToday(), barray);
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
