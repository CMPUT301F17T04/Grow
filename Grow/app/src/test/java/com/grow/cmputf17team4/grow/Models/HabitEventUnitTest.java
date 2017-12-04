package com.grow.cmputf17team4.grow.Models;

import android.location.Location;
import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by chris on 2017-12-04.
 */

public class HabitEventUnitTest {
    private HabitEvent habitEvent;
    private HabitType  habitType;
    private String     id = "fufu", habitTypeID;
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
        String name = habitType.getName();
        habitEvent = new HabitEvent(habitTypeID,id);
        assertEquals(habitEvent.getName(),name);

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
        assertEquals(habitEvent.compareTo(habitEvent2),0);

    }


    @Test
    public void testGetDate(){
        habitType = new HabitType(id);
        habitTypeID = habitType.getUid();
        habitEvent = new HabitEvent(habitTypeID,id);
        assertNotNull(habitEvent.getDate());
    }

}
