package com.grow.cmputf17team4.grow.ModelTests;

import android.test.ActivityInstrumentationTestCase2;

import com.grow.cmputf17team4.grow.Models.Constant;
import com.grow.cmputf17team4.grow.Models.EventList;
import com.grow.cmputf17team4.grow.Models.HabitEvent;
import com.grow.cmputf17team4.grow.User;

import java.util.ArrayList;

/**
 * Created by Infrared on 2017-11-11.
 */

public class EventListTest extends ActivityInstrumentationTestCase2 {
    public EventListTest(){
        super(EventList.class);
    }
    public void testGetUid(){
        User auser = new User("Fufu");
        assertEquals("EventList.GetUid Error", auser.getUid(), 1);

    }
    public void testAdd(){
        HabitEvent auserid = new HabitEvent("fufu");
        assertEquals("EventList.Add Error", auserid.getUid(), "fufu");
    }
    public void testRemove(){
        HabitEvent auserid = new HabitEvent("fufu");
        assertEquals("EventList.Remove Error",auserid.remove(), true);

    }
    public void testCommit(){
        
    }
}
