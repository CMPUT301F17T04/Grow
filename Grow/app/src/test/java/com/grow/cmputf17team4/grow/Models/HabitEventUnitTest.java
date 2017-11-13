package com.grow.cmputf17team4.grow.Models;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Infrared on 2017-11-13.
 */
public class HabitEventUnitTest {
    private HabitEvent habitEvent;
    @Test
    public void TestSetAndGetComment(){
        habitEvent = new HabitEvent("fufu");
        habitEvent.setComment("test");
        assertEquals(habitEvent.getComment(),"test");

    }
    @Test
    public void TestGetUid(){
        habitEvent = new HabitEvent("fufu");
        assertNotNull(habitEvent.getUid());

    }
    @Test
    public void TestSetAndGetName(){
        habitEvent = new HabitEvent("fufu");
        habitEvent.setName("haha");
        assertEquals(habitEvent.getName(),"haha");

    }
    @Test
    public void TestSetAndGetEncodeImage(){
        habitEvent = new HabitEvent("fufu");
        habitEvent.setEncodedImage("haha");
        assertEquals(habitEvent.getEncodedImage(),"haha");

    }
    @Test
    public void TestSetAndGetAttachedLocation(){
        habitEvent = new HabitEvent("fufu");
        habitEvent.setLocation(1);
        assertEquals(habitEvent.isAttachedLocation(),true);

    }

    @Test
    public void TestGenerateUid(){
        habitEvent = new HabitEvent("fufu");
        assertNotNull(habitEvent.generateUid());

    }
    @Test
    public void TestGetIndex(){
        habitEvent = new HabitEvent("fufu");
        assertEquals(habitEvent.getIndex(),"habitEvent");

    }
    @Test
    public void TestCompareTo(){
        habitEvent = new HabitEvent("fufu");
        HabitEvent habitEvent2 = new HabitEvent("haha");
        assertEquals(habitEvent.compareTo(habitEvent2),0);

    }
    @Test
    public void testIsChanged(){
        habitEvent = new HabitEvent("fufu");
        assertEquals(habitEvent.isChanged(),false);
    }

    @Test
    public void testGetDate(){
        habitEvent = new HabitEvent("fufu");
        assertNotNull(habitEvent.getDate());
    }

}
