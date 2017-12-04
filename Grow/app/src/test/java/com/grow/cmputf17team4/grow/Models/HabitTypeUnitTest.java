package com.grow.cmputf17team4.grow.Models;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * Created by Chris
 */

public class HabitTypeUnitTest {
    private HabitType habitType;
    String id = "fufu", name = "coding";

    @Test
    public void testCreateHabitType(){
        habitType = new HabitType(id);
        assertNotNull(habitType);
    }

    @Test
    public void testSetAndGetName(){
        habitType = new HabitType(id);
        habitType.setName(name);
        assertEquals(habitType.getName(),name);
    }
    @Test
    public void testSetAndGetReason(){
        String reason = "cool";
        habitType = new HabitType(id);
        habitType.setReason(reason);
        assertEquals(habitType.getReason(),reason);

    }
    @Test
    public void testSetAndGetDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date startDate = calendar.getTime();
        habitType = new HabitType(id);
        habitType.setStartDate(startDate);
        assertEquals(habitType.getStartDate(),startDate);

    }
    @Test
    public void testSetAndGetRepeat(){
        habitType = new HabitType(id);
        boolean repeat = true;
        int i = 1;
        habitType.setRepeat(i,repeat);
        assertEquals(habitType.getRepeat(i),repeat);

    }
    @Test
    public void testHasEventToday(){
        habitType = new HabitType(id);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        boolean repeat = true;
        habitType.setRepeat(day,repeat);
        assertEquals(habitType.hasEventToday(),true);

    }

    @Test
    public void testGetNextEventDay(){
        habitType = new HabitType(id);
        habitType.setName("Name");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        calendar.add(Calendar.DATE,1);
        Date startDate = calendar.getTime();
        boolean repeat = true;
        habitType.setStartDate(startDate);
        habitType.setRepeat(day,repeat);
        calendar.add(Calendar.DATE,6);
        Date nextDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(format.format(habitType.getNextEventDay()),format.format(nextDate));

    }

    @Test
    public void testCompareTo(){
        habitType = new HabitType(id);
        HabitType habitType2 = new HabitType(id);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        boolean repeat = true;
        habitType.setRepeat(day,repeat);
        habitType2.setRepeat((day+1)%7,repeat);
        assert(habitType.compareTo(habitType2)<0);
    }
    @Test
    public void testAlreadyDone(){
        habitType = new HabitType(id);
        assertEquals(habitType.alreadyDone(),false);
    }

    @Test
    public void testGetUid(){
        habitType = new HabitType(id);
        assertNotNull(habitType.getUid());
    }
    @Test
    public void testGetMostRecentEvent(){
        habitType = new HabitType(id);
        assertNull(habitType.getMostRecentEvent());
    }



}