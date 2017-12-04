package com.grow.cmputf17team4.grow.Models;

import android.location.Location;
import android.support.annotation.NonNull;

import com.grow.cmputf17team4.grow.Controllers.DataManager;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by chris on 2017-12-04.
 */

public class HabitEventUnitTest {
    private HabitEvent habitEvent;
    private HabitType  habitType;
    private final String id = Constant.TEST_USER_NAME;
    private String habitTypeID;


    @Before
    public void setUp(){
        DataManager.prepareForTesting();
    }


    @Test
    public void TestSetAndGetComment(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        habitEvent.setComment("test");
        assertEquals(habitEvent.getComment(),"test");

    }
    @Test
    public void TestGetUserId(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        assertEquals(habitEvent.getUserId(),id);

    }
    @Test
    public void TestGetHabitTypeID(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        assertEquals(habitEvent.getHabitTypeID(),habitTypeID);
    }
    @Test
    public void TestGetName(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        habitEvent.setName("chris");
        assertEquals(habitEvent.getName(),"chris");

    }
    @Test
    public void TestSetAndGetEncodeImage(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        habitEvent.setEncodedImage("image");
        assertEquals(habitEvent.getEncodedImage(),"image");

    }
    @Test
    public void TestCompareTo(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        HabitEvent habitEvent2 = new HabitEvent(habitTypeID,id);
        habitEvent2.setName("Chris");
        habitEvent.setName("Chris");
        assertEquals(habitEvent.compareTo(habitEvent2),0);

    }


    @Test
    public void testGetDate(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        assertNotNull(habitEvent.getDate());
    }
    @Test
    public void testGetHabitType(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        DataManager.getInstance().getHabitList().add(habitType);
        DataManager.getInstance().getEventList().add(habitEvent);
        assertEquals(habitEvent.getHabitType(),habitType);
    }

}
