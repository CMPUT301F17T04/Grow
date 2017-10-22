package com.grow.cmputf17team4.grow;

import android.test.ActivityInstrumentationTestCase2;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chris on 2017-10-21.
 */

public class EventTest extends ActivityInstrumentationTestCase2{
    public EventTest(){
        super(com.grow.cmputf17team4.grow.HabitEvent.class);
    }

    public void testGetId(){
        HabitEvent auserid = new HabitEvent(123456);

        assertEquals("HabitEvent.GetId Error", auserid.getId(),1);
    }
    public void testSetId(){
        HabitEvent auserid = new HabitEvent(123456);
        auserid.setId(110);
        assertEquals("HabitEvent.SetId Error", auserid.getId(), 110);

    }

    public void testGetUserId(){
        HabitEvent auserid = new HabitEvent(123456);
        assertEquals("HabitEvent.GetUserId Error", auserid.getUserId(), 123456);
    }

    public void testSetUserId(){
        HabitEvent auserid = new HabitEvent(123456);
        auserid.setUserId(110);
        assertEquals("HabitEvent.SetUserId Error", auserid.getUserId(), 110);
    }

    public void testGetStartTime(){
        HabitEvent auserid = new HabitEvent(123456);
        Date d = new Date(2017,10,22);
        assertEquals("HabitEvent.GetStartTime Error", auserid.getStartTime(), d);
    }

    public void testSetStartTime(){
        HabitEvent auserid = new HabitEvent(123456);
        Date d = new Date(2017,10,22);
        auserid.setStartTime(d);
        assertEquals("HabitEvent.SetStartTime Error", auserid.getStartTime(), d);
    }

    public void testGetDateToRepresent(){
        HabitEvent auserid = new HabitEvent(123456);
        assertEquals("HabitEvent.GetDateToRepresent Error", auserid.getDateToRepresent(), "2017-10-22");
    }

    public void testSetDateToRepresent(){
        HabitEvent auserid = new HabitEvent(123456);
        auserid.setDateToRepresent("2017-10-22");
        assertEquals("HabitEvent.SetDateToRepresent Error", auserid.getDateToRepresent(), "2017-10-22");
    }

    public void testGetComment(){
        HabitEvent auserid = new HabitEvent(123456);
        assertEquals("HabitEvent.GetComment Error", auserid.getComment(), "zhai is dead");
    }

    public void testSetComment(){
        HabitEvent auserid = new HabitEvent(123456);
        auserid.setComment("pig");
        assertEquals("HabitEvent.SetComment Error", auserid.getComment(), "pig");
    }

    public void testIsFinished(){
        HabitEvent auserid = new HabitEvent(123456);
        assertFalse(auserid.isFinished());
    }

    public void testGetDuration(){
        HabitEvent auserid = new HabitEvent(123456);

        assertEquals("HabitEvent.GetDuration Error", auserid.getDuration(), 10.0);
    }

    public void testSetDuration(){
        HabitEvent auserid = new HabitEvent(123456);
        auserid.setDuration(100);
        assertEquals("HabitEvent.SetDuration Error", auserid.getDuration(), 100.0);
    }

    public void testGetPhoto(){
        HabitEvent auserid = new HabitEvent(123456);
        byte[] b = auserid.getPhoto();
        assertEquals("HabitEvent.GetPhoto Error", auserid.getPhoto(), b);
    }

    public void testSetPhoto(){
        HabitEvent auserid = new HabitEvent(123456);
        byte[] b = new byte[10];
        b[0] = 10;
        auserid.setPhoto(b);
        byte[] c = auserid.getPhoto();
        assertEquals("HabitEvent.SetPhoto Error",c[0], 10);

    }

}
